package com.example.mtbs.exception;

import lombok.Getter;

@Getter
public class UserExistsByEmailException {

    private String message;

    public UserExistsByEmailException(String message) {
        this.message = message;
    }
}
