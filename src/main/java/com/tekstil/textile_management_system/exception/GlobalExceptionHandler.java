package com.tekstil.textile_management_system.exception;

import com.tekstil.textile_management_system.dto.BaseResponse;
import com.tekstil.textile_management_system.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseException(BaseException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(BaseResponse.error(ex.getStatus(), ex.getMessage()));
    }
    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, List<String>>>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));
        return ResponseEntity
                .badRequest()
                .body(BaseResponse.error(400, "Validation failed", errors));
    }
    // json errors
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<Void>> handleBadRequest(
            HttpMessageNotReadableException ex) {

        return ResponseEntity
                .badRequest()
                .body(BaseResponse.error(400, "Invalid request format"));
    }
}