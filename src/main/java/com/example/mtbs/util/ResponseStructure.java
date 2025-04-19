package com.example.mtbs.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ResponseStructure<T>{

    private int statusCode;
    private String message;
    private T data;

}
