package com.example.mtbs.exception;

import com.example.mtbs.util.ErrorStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class userExeceptionHandler {

    private final RestResponseBuilder restResponseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handlerUserExitsByEmailException(UserExistsByEmailException ex){
        return restResponseBuilder.error(HttpStatus.OK,ex.getMessage());
    }
}
