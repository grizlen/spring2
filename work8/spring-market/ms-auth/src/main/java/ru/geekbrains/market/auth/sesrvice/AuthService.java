package ru.geekbrains.market.auth.sesrvice;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.auth.model.AuthRequestDTO;
import ru.geekbrains.market.auth.model.AuthResponseDTO;
import ru.geekbrains.market.auth.model.Role;
import ru.geekbrains.market.auth.model.User;
import ru.geekbrains.market.auth.repository.RoleRepository;
import ru.geekbrains.market.auth.repository.UserRepository;
import ru.geekbrains.market.core.exceptions.UserNotFoudException;
import ru.geekbrains.market.core.model.UserInfo;
import ru.geekbrains.market.core.service.ITokenService;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenService tokenService;


    public AuthResponseDTO signUp(AuthRequestDTO authRequestDTO) {
        User user = new User();
        user.setLogin(authRequestDTO.getLogin());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(Collections.singletonList(role));
        UserInfo userInfo = userToUserInfo(userRepository.save(user));
        return new AuthResponseDTO(tokenService.generateToken(userInfo));
    }

    public AuthResponseDTO logIn(AuthRequestDTO authRequestDTO) {
        User user = userRepository.findByLogin(authRequestDTO.getLogin())
                .orElseThrow(() -> new UserNotFoudException(
                        "User: " + authRequestDTO.getLogin() + "not found"));
        if (!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            throw new UserNotFoudException("Invalid password.");
        }
        UserInfo userInfo = userToUserInfo(user);
        return new AuthResponseDTO(tokenService.generateToken(userInfo));
    }

    private UserInfo userToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setLogin(user.getLogin());
        userInfo.setRoles(user.getRole().stream().map(role -> role.getName()).collect(Collectors.toList()));
        return userInfo;
    }
}
