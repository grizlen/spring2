package ru.geekbrains.market.core.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    Long id;
    String login;
    List<String> roles;
}
