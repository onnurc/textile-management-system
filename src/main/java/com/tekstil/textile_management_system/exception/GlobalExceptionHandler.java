package com.tekstil.textile_management_system.exception;

import com.tekstil.textile_management_system.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error(404, ex.getMessage()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleBadRequest(IllegalArgumentException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error(400, ex.getMessage()));
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<BaseResponse<Void>> handleGeneral(Exception ex){
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(BaseResponse.error(500,"Beklenmeyen bir hata oluştu"));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> errorResponse(){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error(500,"Beklenmeyen bir hata oluştu"));
    }

    }

