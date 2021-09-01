package ru.geekbrains.market.core.service;

import ru.geekbrains.market.core.dto.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo userInfo);
    UserInfo parseToken(String token);
}
