package com.library.inventory.controllers;

import com.library.inventory.dto.BorrowItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1")
public interface LedgerApi {
    @GetMapping(value = "/user/borrowed/{userId}")
    ResponseEntity<List<BorrowItemDTO>> getBorrowedItemsByUserId(@PathVariable("userId") final String userId);
}
