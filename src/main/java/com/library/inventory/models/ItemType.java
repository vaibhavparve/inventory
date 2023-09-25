package com.library.inventory.models;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public enum ItemType implements Serializable {
    BOOK("BOOK"),

    VHS("VHS"),

    CD("CD"),

    DVD("DVD");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }
}