package com.library.inventory.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BaseException extends ResponseStatusException {
    private final String message;
    private final ExceptionCodes exceptionCode;

    public BaseException(HttpStatus status, ExceptionCodes exceptionCodes, String message) {
        super(status);
        this.message = message;
        this.exceptionCode = exceptionCodes;
    }
}
