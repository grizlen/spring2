package ru.geekbrains.springshop.libsecurity.reprositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.springshop.libsecurity.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}
