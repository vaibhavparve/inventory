package com.library.inventory.exception;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public enum ExceptionCodes implements Serializable {
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NOT_FOUND("Not Found");

    private final String value;

    ExceptionCodes(String value) {
        this.value = value;
    }
}
