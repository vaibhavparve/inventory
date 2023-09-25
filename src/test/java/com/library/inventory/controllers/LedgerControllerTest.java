package com.library.inventory.controllers;

import com.google.common.collect.Lists;
import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.exception.InternalServerError;
import com.library.inventory.services.LibraryLedger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LedgerControllerTest {

    @Mock
    private LibraryLedger libraryLedger;

    @InjectMocks
    private LedgerController sut;

    @Test
    void getBorrowedItemsByUserId() {
        when(libraryLedger.getBorrowedItems(any())).thenReturn(Lists.newArrayList());

        ResponseEntity<List<BorrowItemDTO>> response = sut.getBorrowedItemsByUserId(any());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void getBorrowedItemsByUserId500() {
        when(libraryLedger.getBorrowedItems(any())).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.getBorrowedItemsByUserId(any()));

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }
}