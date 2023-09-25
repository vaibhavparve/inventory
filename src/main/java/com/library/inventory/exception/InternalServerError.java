package com.library.inventory.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends BaseException {
    public InternalServerError(HttpStatus status, ExceptionCodes exceptionCodes, String message) {
        super(status, exceptionCodes, message);
    }
}
