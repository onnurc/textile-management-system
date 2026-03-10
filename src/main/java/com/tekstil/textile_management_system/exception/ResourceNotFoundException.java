package com.tekstil.textile_management_system.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
