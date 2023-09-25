package com.library.inventory.controllers;

import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.dto.InventoryDTO;
import com.library.inventory.dto.ItemAvailabilityDTO;
import com.library.inventory.dto.OverDueItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface InventoryApi {


    /*get available inventory */
    @GetMapping(value = "/inventory/available")
    ResponseEntity<InventoryDTO> getLoanableInventory();


    /*get overdue items */
    @GetMapping(value = "/inventory/overdue")
    ResponseEntity<OverDueItemDTO> getOverdueInventory();

    /*Determine if a book is available*/
    @GetMapping(value = "/inventory/available/{itemType}/{title}")
    ResponseEntity<ItemAvailabilityDTO> getItemAvailability(@PathVariable("itemType") final String itemType,
                                                            @PathVariable("title") final String title);

    @GetMapping(value = "/inventory/borrow/{itemType}/{title}/{userId}")
    ResponseEntity<BorrowItemDTO> borrowItem(@PathVariable("itemType") final String itemType,
                                             @PathVariable("title") final String title,
                                             @PathVariable("userId") final String userId);

    @GetMapping(value = "/inventory/return/{itemType}/{title}/{userId}")
    ResponseEntity<BorrowItemDTO> returnItem(@PathVariable("itemType") final String itemType,
                                             @PathVariable("title") final String title,
                                             @PathVariable("userId") final String userId);
}
