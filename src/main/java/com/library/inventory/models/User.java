package com.library.inventory.models;

import com.google.common.collect.Lists;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Setter
@AllArgsConstructor
public class User implements Serializable {
    private String userId;

    private String name;

    private List<BaseInventory> borrowedItems = Lists.newArrayList();
}
