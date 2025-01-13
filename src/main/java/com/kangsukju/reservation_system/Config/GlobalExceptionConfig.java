package com.kangsukju.reservation_system.Config;

import com.kangsukju.reservation_system.Dto.ResultDto;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionConfig {
    // 1. 특정 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultDto<String>> handleRuntimeException(RuntimeException ex, WebRequest request){
        return  new ResponseEntity<>(
                ResultDto.of("400",ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultDto<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(
                ResultDto.of("400", errors),
                HttpStatus.BAD_REQUEST
        );

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultDto<String>> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ResultDto.of("500", "Internal Server Error: " + ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );


    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDto<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                ResultDto.of("400", "유효하지 않는 " + ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResultDto<String>> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(
                ResultDto.of("403", "접근 불가 " + ex.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<String> handleOptimisticLockException(OptimisticLockException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("다른 사용자가 데이터를 이용중에 있습니다. 다시 시도해주세요.");
    }
}
