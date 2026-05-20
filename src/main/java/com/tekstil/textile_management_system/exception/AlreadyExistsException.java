package com.tekstil.textile_management_system.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends BaseException {
    public AlreadyExistsException(String message){

        super(message, HttpStatus.CONFLICT.value());
    }

}
