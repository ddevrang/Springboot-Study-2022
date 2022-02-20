package com.example.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // ※ 전역 예외 처리 구현
    // 영화 데이터 검색 시 쿼리 조건에 해당하는 데이터가 전혀 없을 때,
    // 애플리케이션 서비스 레이어에서 throw new exception 으로 예외 발생
    @ExceptionHandler(value = EmptyDataException.class)
    public ResponseEntity<CommonResponse> handleEmptyDataException(EmptyDataException e) {
        log.info("handleEmptyDataException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.NOT_FOUND.getCode())
                .message(e.getMessage())
                .status(ErrorCode.NOT_FOUND.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException e) {

        log.info("handleRuntimeException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}