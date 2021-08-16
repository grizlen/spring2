package ru.geekbrains.springshop.libcore.exceptions;

public class UserNotFoudException extends RuntimeException {
    public UserNotFoudException(String message) {
        super(message);
    }
}
