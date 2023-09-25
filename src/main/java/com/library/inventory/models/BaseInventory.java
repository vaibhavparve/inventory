package com.library.inventory.models;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BaseInventory implements Serializable {
    private UUID uniqueId;

    private ItemType itemType;

    private String title;

    private Status status;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private String userId;
}
