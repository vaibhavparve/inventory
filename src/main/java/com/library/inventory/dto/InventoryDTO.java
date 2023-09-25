package com.library.inventory.dto;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@Data
public class InventoryDTO implements Serializable {
    private List<String> titles = Lists.newArrayList();

    public InventoryDTO(List<String> titles) {
        this.titles = titles;
    }
}
