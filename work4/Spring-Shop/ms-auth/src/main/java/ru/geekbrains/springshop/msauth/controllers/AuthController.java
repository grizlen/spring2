package ru.geekbrains.springshop.msauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springshop.libcore.dtos.UserInfo;
import ru.geekbrains.springshop.libcore.repositories.RedisRepository;
import ru.geekbrains.springshop.libcore.services.ITokenService;
import ru.geekbrains.springshop.msauth.dtos.AuthRequestDto;
import ru.geekbrains.springshop.msauth.dtos.AuthResponseDto;
import ru.geekbrains.springshop.msauth.entities.User;
import ru.geekbrains.springshop.msauth.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private RedisRepository redisRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody AuthRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        List<String> roles = new ArrayList<>();
        user.getRole().forEach(role -> roles.add(role.getName()));
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setLogin(user.getLogin());
        userInfo.setRoles(roles);
        String token = iTokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }
    @GetMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        redisRepository.setKey(token);
    }
}
