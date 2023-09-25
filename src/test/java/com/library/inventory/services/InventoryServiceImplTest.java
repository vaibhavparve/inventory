package com.library.inventory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {
    @Mock
    private LibraryLedgerImpl ledgerService;

    @Test
    void getCurrentInventory() {
    }

    @Test
    void getOverdueInventory() {
    }

    @Test
    void getItemAvailability() {
    }

    @Test
    void borrowItem() {
    }

    @Test
    void returnItem() {
    }
}