package ru.geekbrains.market.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
