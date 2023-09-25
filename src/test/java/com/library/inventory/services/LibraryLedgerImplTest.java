package com.library.inventory.services;

import com.google.common.collect.Lists;
import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.models.BaseInventory;
import com.library.inventory.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LibraryLedgerImplTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private LibraryLedgerImpl sut;

    @Test
    void getBorrowedItems() {
        User user = new User("1", "tom", Lists.newArrayList());
        when(userService.getUser("1")).thenReturn(user);

        List<BorrowItemDTO> response = sut.getBorrowedItems("1");

        assertAll(
                () -> assertEquals(0, response.size()),
                () -> verify(userService, atLeast(1)).getUser("1")
        );

    }

    @Test
    void testUpdateUserBorrowings() {
        User user = new User("1", "tom", Lists.newArrayList());

        when(userService.getUser("1")).thenReturn(user);

        sut.updateUserBorrowings("1", new BaseInventory());

        verify(userService, atLeast(1)).getUser("1");
    }

    @Test
    void testGetBorrowedItems() {
        User user = new User("1", "tom", Lists.newArrayList());

        when(userService.getUser("1")).thenReturn(user);

        sut.getBorrowedItems("1");

        verify(userService, atLeast(1)).getUser("1");
    }
}