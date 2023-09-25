package com.library.inventory.models;

import java.io.Serializable;

public enum Status implements Serializable {
    LOANED,

    RETURNED,

    AVAILABLE;
}
