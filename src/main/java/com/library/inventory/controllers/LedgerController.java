package com.library.inventory.controllers;

import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.exception.ExceptionCodes;
import com.library.inventory.exception.InternalServerError;
import com.library.inventory.services.LibraryLedger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LedgerController implements LedgerApi {
    private final LibraryLedger libraryLedger;

    @Override
    public ResponseEntity<List<BorrowItemDTO>> getBorrowedItemsByUserId(String userId) {
        return Optional.ofNullable(libraryLedger.getBorrowedItems(userId))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR,
                        ExceptionCodes.INTERNAL_SERVER_ERROR,
                        "unable to process the current request."));
    }
}
