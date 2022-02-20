package com.example.study.exception;

public class EmptyDataException extends RuntimeException {

    private ErrorCode errorCode;

    public EmptyDataException() {
        super();
    }

    public EmptyDataException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }
}
