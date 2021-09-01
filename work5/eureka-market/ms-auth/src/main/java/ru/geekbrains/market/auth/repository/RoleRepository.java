package ru.geekbrains.market.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.market.auth.model.Role;
import ru.geekbrains.market.auth.model.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
