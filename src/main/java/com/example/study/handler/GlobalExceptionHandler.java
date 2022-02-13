package com.example.study.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e) {
        // 추후 로깅처리 및 ResponseDto 만들 예정.
        return e.getMessage();
    }
}