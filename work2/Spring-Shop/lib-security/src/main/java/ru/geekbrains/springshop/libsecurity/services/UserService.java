package ru.geekbrains.springshop.libsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.springshop.libcore.exceptions.UserNotFoudException;
import ru.geekbrains.springshop.libdb.dto.AuthDTO;
import ru.geekbrains.springshop.libsecurity.entities.Role;
import ru.geekbrains.springshop.libsecurity.entities.User;
import ru.geekbrains.springshop.libsecurity.reprositories.RoleRepository;
import ru.geekbrains.springshop.libsecurity.reprositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        if (user.getRoles().stream().findAny().isEmpty()) {
            user.getRoles().add(role);
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByUserName(login);
    }

    public User findByLoginAndPassword(AuthDTO authDTO) {
        User userEntity = findByLogin(authDTO.getLogin());
        if (userEntity != null) {
            if (passwordEncoder.matches(authDTO.getPassword(), userEntity.getPassword())) {
                return userEntity;
            }
        }
        throw new UserNotFoudException(String.format("User '%s' not found.", authDTO.getLogin()));
    }
}
