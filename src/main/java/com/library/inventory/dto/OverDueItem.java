package com.library.inventory.dto;

import com.library.inventory.models.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OverDueItem implements Serializable {
    private String title;

    private ItemType itemType;

    private LocalDate dueDate;
}
