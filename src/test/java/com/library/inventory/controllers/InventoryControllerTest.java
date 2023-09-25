package com.library.inventory.controllers;

import com.google.common.collect.Lists;
import com.library.inventory.dto.*;
import com.library.inventory.exception.InternalServerError;
import com.library.inventory.services.InventoryServiceImpl;
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
class InventoryControllerTest {

    @Mock
    private InventoryServiceImpl inventoryService;

    @InjectMocks
    private InventoryController sut;

    @Test
    void getLoanableInventory() {
        var random = List.of("Random");
        when(inventoryService.getCurrentInventory()).thenReturn(new InventoryDTO(random));

        ResponseEntity<InventoryDTO> response = sut.getLoanableInventory();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void getLoanableInventory500() {
        when(inventoryService.getCurrentInventory()).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.getLoanableInventory());

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }

    @Test
    void getOverdueInventory() {
        when(inventoryService.getOverdueInventory()).thenReturn(new OverDueItemDTO(Lists.newArrayList(new OverDueItem())));

        ResponseEntity<OverDueItemDTO> response = sut.getOverdueInventory();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void getOverdueInventoryEmpty() {
        when(inventoryService.getOverdueInventory()).thenReturn(new OverDueItemDTO(Lists.newArrayList()));

        ResponseEntity<OverDueItemDTO> response = sut.getOverdueInventory();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody())),
                () -> assertEquals(0, response.getBody().getOverdueItems().size())
        );
    }

    @Test
    void getOverdueInventory500() {
        when(inventoryService.getOverdueInventory()).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.getOverdueInventory());

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }

    @Test
    void getItemAvailabilityPositive() {
        when(inventoryService.getItemAvailability(any(), any())).thenReturn(new ItemAvailabilityDTO());

        ResponseEntity<ItemAvailabilityDTO> response = sut.getItemAvailability(any(), any());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void getItemAvailability500() {
        when(inventoryService.getItemAvailability(any(), any())).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.getItemAvailability(any(), any()));

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }

    @Test
    void borrowItemPositive() {
        when(inventoryService.borrowItem(any(), any(), any())).thenReturn(new BorrowItemDTO());

        ResponseEntity<BorrowItemDTO> response = sut.borrowItem(any(), any(), any());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void borrowItem500() {
        when(inventoryService.borrowItem(any(), any(), any())).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.borrowItem(any(), any(), any()));

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }

    @Test
    void returnItem() {
        when(inventoryService.returnItem(any(), any(), any())).thenReturn(new BorrowItemDTO());

        ResponseEntity<BorrowItemDTO> response = sut.returnItem(any(), any(), any());

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(Objects.nonNull(response.getBody()))
        );
    }

    @Test
    void returnItem500() {
        when(inventoryService.returnItem(any(), any(), any())).thenReturn(null);

        InternalServerError response = assertThrows(InternalServerError.class,
                () -> sut.returnItem(any(), any(), any()));

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("unable to process the current request.",
                        response.getMessage())
        );
    }
}