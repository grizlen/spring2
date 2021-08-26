package ru.geekbrains.market.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    Long id;
    String login;
    List<String> roles;
}
