package com.library.inventory.services;

import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.dto.InventoryDTO;
import com.library.inventory.dto.ItemAvailabilityDTO;
import com.library.inventory.dto.OverDueItemDTO;

public interface InventoryService {
    InventoryDTO getCurrentInventory();

    OverDueItemDTO getOverdueInventory();

    ItemAvailabilityDTO getItemAvailability(String itemType, String name);

    BorrowItemDTO borrowItem(String itemType, String title, String userId);

    BorrowItemDTO returnItem(String itemType, String title, String userId);
}
