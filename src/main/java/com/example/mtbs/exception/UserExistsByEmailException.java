package com.example.mtbs.exception;

public class UserExistsByEmailException extends RuntimeException {

    private String message;

    public UserExistsByEmailException(String message){
        this.message= message;
    }
}
