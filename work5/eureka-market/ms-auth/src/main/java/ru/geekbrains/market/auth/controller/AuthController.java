package ru.geekbrains.market.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.auth.model.AuthRequestDTO;
import ru.geekbrains.market.auth.model.AuthResponseDTO;
import ru.geekbrains.market.auth.model.User;
import ru.geekbrains.market.auth.service.UserService;
import ru.geekbrains.market.core.dto.UserInfo;
import ru.geekbrains.market.core.repositories.RedisRepository;
import ru.geekbrains.market.core.service.ITokenService;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class AuthController {

    private final UserService userService;
    private final ITokenService tokenService;
    private final RedisRepository redisRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDTO signUp(@RequestBody AuthRequestDTO request) {
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        userService.saveUser(user);
        UserInfo userInfo = userToUserInfo(user);
        return new AuthResponseDTO(tokenService.generateToken(userInfo));
    }

    @PostMapping("/login")
    public AuthResponseDTO logIn(@RequestBody AuthRequestDTO request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());

        UserInfo userInfo = userToUserInfo(user);
        return new AuthResponseDTO(tokenService.generateToken(userInfo));
    }

    @GetMapping("/logout")
    public void logOut(@RequestHeader("Authorization") String token) {
        if (token != null) {
            redisRepository.setKey(token);
        }
    }

    private UserInfo userToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setLogin(user.getLogin());
        userInfo.setRoles(user.getRole().stream().map(role -> role.getName()).collect(Collectors.toList()));
        return userInfo;
    }
}
