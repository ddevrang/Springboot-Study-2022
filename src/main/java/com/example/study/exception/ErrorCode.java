package com.example.study.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_FOUND(404, "not_found", " NOT FOUND."),
    INTERNAL_SERVER_ERROR(500, "internal_server_error", " INTERNAL SERVER ERROR.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}