package com.example.mtbs.exception.handller;

import com.example.mtbs.exception.UserExistsByEmailException;
import com.example.mtbs.exception.UserNotFoundByEmailException;
import com.example.mtbs.util.ErrorStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserExeceptionHandler {

    private final RestResponseBuilder restResponseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handlerUserExitsByEmailException(UserExistsByEmailException ex){
        return restResponseBuilder.error(HttpStatus.OK,ex.getMessage(), "");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleUserNotFoundByEmailException(UserNotFoundByEmailException ex){
        return restResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "");
    }
}
