package com.tekstil.textile_management_system.exception;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseException(BaseException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(BaseResponse.error(ex.getStatus(), ex.getMessage()));
    }
}