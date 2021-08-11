package ru.geekbrains.springshop.libsecurity.reprositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.springshop.libsecurity.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
