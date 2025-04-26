package com.example.mtbs.exception.handller;

import com.example.mtbs.exception.TheaterNotFoundByIdException;
import com.example.mtbs.util.ErrorStructure;
import com.example.mtbs.util.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TheaterExceptionHandler {

    private final RestResponseBuilder restResponseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleTheaterNotFoundByIdException(TheaterNotFoundByIdException ex){
        return restResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage() , "Theater with the requested ID not found");
    }

}
