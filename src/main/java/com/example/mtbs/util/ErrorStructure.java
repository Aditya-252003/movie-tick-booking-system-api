package com.example.mtbs.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorStructure <T>    {

    private int statusCode;
    private String errorMessage;
    private T error;


}
