package ru.geekbrains.market.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.auth.model.AuthRequestDTO;
import ru.geekbrains.market.auth.model.AuthResponseDTO;
import ru.geekbrains.market.auth.sesrvice.AuthService;
import ru.geekbrains.market.core.repository.RedisRepository;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RedisRepository redisRepository;

    @PostMapping("/signup")
    public AuthResponseDTO signUp(@RequestBody AuthRequestDTO request) {
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO logIn(@RequestBody AuthRequestDTO request) {
        return authService.logIn(request);
    }

    @GetMapping("/logout")
    public void logOut(@RequestHeader("Authorization") String token) {
        if (token != null) {
            redisRepository.setKey(token);
        }
    }
}
