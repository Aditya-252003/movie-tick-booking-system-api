package com.example.mtbs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TheaterNotFoundByIdException {
    private String message;
}
