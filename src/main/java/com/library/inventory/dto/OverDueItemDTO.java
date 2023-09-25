package com.library.inventory.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class OverDueItemDTO implements Serializable {
    List<OverDueItem> overdueItems = Lists.newArrayList();
}
