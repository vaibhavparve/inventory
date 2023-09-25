package com.library.inventory.dto;

import com.library.inventory.models.ItemType;
import com.library.inventory.models.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@ToString
@Setter
public class BorrowItemDTO implements Serializable {
    private String userId;

    private LocalDate dueDate;

    private ItemType itemType;

    private Status status;

    private String name;
}
