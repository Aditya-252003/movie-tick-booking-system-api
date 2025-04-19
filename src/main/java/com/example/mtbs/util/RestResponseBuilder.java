package com.example.mtbs.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestResponseBuilder{

    public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus statusCode, String message, T data) {
        ResponseStructure<T> responseStructure = ResponseStructure.<T>builder().statusCode(statusCode.value()).message(message).data(data)
                .build();

        return ResponseEntity.status(statusCode).body(responseStructure);
    }

    public ResponseEntity<ErrorStructure> error(HttpStatus statusCode, String message){
        return ResponseEntity.status(statusCode).body(ErrorStructure.builder()
                .statusCode(statusCode.value())
                .errorMessage(message)
                .build());
    }
}
