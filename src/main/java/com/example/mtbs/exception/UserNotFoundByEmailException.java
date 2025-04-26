package com.example.mtbs.exception;

import lombok.Getter;

@Getter
public class UserNotFoundByEmailException {

    private String message;

    public UserNotFoundByEmailException(String message) {
        this.message = message;
    }
}
