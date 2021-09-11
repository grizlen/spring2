package ru.geekbrains.market.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserNotFound(UserNotFoudException e) {
        return new ResponseEntity<>(
                new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(
                new MarketError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getClass().getName() + ": " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
