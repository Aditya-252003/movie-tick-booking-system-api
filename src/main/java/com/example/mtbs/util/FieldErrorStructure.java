package com.example.mtbs.util;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldErrorStructure<T> {

    private int statusCode;
    private String errorMessage;
    private T data;

}
