package com.library.inventory.services;

import com.google.common.collect.Lists;
import com.library.inventory.dto.BorrowItemDTO;
import com.library.inventory.models.BaseInventory;
import com.library.inventory.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryLedgerImpl implements LibraryLedger {
    private final UserServiceImpl userService;

    @Override
    public void updateUserBorrowings(String userId, BaseInventory baseInventory) {
        User user = userService.getUser(userId);

        Optional<BaseInventory> borrowed = user.getBorrowedItems().stream()
                .filter(item -> item.getUniqueId().equals(baseInventory.getUniqueId())).findFirst();

        borrowed.ifPresentOrElse(item -> {
                    List<BaseInventory> borrowedItems = user.getBorrowedItems();
                    borrowedItems.remove(item);
                    user.setBorrowedItems(borrowedItems);
                },
                () -> {
                    List<BaseInventory> borrowedItems = user.getBorrowedItems();
                    borrowedItems.add(baseInventory);
                    user.setBorrowedItems(borrowedItems);
                });

        userService.updateUser(user);
    }

    @Override
    public List<BorrowItemDTO> getBorrowedItems(String userId) {
        List<BorrowItemDTO> borrowedList = Lists.newArrayList();

        User user = userService.getUser(userId);
        user.getBorrowedItems().forEach(item -> {
                    BorrowItemDTO borrowItemDTO = new BorrowItemDTO();

                    borrowItemDTO.setUserId(user.getUserId());
                    borrowItemDTO.setItemType(item.getItemType());
                    borrowItemDTO.setDueDate(item.getDueDate());
                    borrowItemDTO.setStatus(item.getStatus());
                    borrowItemDTO.setName(item.getTitle());

                    borrowedList.add(borrowItemDTO);
                }
        );

        return borrowedList;
    }
}
