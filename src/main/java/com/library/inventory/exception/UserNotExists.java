package com.library.inventory.exception;

import org.springframework.http.HttpStatus;

public class UserNotExists extends BaseException {

    public UserNotExists(HttpStatus status, ExceptionCodes exceptionCodes, String message) {
        super(status, exceptionCodes, message);
    }
}
