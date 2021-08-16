package ru.geekbrains.springshop.libcore.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String login;
    private List<String> Roles;
}
