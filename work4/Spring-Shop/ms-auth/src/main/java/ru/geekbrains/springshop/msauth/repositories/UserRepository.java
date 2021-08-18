package ru.geekbrains.springshop.msauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.springshop.msauth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
