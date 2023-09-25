package com.library.inventory.controllers;


import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.dto.InventoryDTO;
import com.library.inventory.dto.ItemAvailabilityDTO;
import com.library.inventory.dto.OverDueItemDTO;
import com.library.inventory.exception.ExceptionCodes;
import com.library.inventory.exception.InternalServerError;
import com.library.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InventoryController implements InventoryApi {

    private final InventoryService inventoryService;

    @Override
    public ResponseEntity<InventoryDTO> getLoanableInventory() {
        return Optional.ofNullable(inventoryService.getCurrentInventory())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }

    @Override
    public ResponseEntity<OverDueItemDTO> getOverdueInventory() {
        return Optional.ofNullable(inventoryService.getOverdueInventory())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }

    @Override
    public ResponseEntity<ItemAvailabilityDTO> getItemAvailability(String itemType, String title) {
        return Optional.ofNullable(inventoryService.getItemAvailability(itemType, title))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }

    @Override
    public ResponseEntity<BorrowItemDTO> borrowItem(String itemType, String title, String userId) {
        return Optional.ofNullable(inventoryService.borrowItem(itemType, title, userId))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }

    @Override
    public ResponseEntity<BorrowItemDTO> returnItem(String itemType, String title, String userId) {
        return Optional.ofNullable(inventoryService.returnItem(itemType, title, userId))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }
}