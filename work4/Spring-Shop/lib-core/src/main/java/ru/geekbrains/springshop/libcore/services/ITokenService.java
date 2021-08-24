package ru.geekbrains.springshop.libcore.services;

import ru.geekbrains.springshop.libcore.dtos.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo userInfo);

    UserInfo parseToken(String token);
}
