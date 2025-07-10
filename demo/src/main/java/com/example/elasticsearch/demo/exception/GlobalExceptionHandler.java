package com.example.elasticsearch.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            Exception.class
    )
    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
