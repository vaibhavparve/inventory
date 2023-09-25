package com.library.inventory.services;

import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.models.BaseInventory;

import java.util.List;

public interface LibraryLedger {
    void updateUserBorrowings(String userId, BaseInventory baseInventory);

    List<BorrowItemDTO> getBorrowedItems(String userId);
}
