package com.library.inventory.services;

import com.google.common.collect.Lists;
import com.library.inventory.dto.*;
import com.library.inventory.models.BaseInventory;
import com.library.inventory.models.ItemType;
import com.library.inventory.models.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private LibraryLedgerImpl ledgerService;
    private final Map<ItemType, List<BaseInventory>> currentInventory = new ConcurrentHashMap<>();

    InventoryServiceImpl(LibraryLedgerImpl libraryLedgerImpl) {
        this.ledgerService = libraryLedgerImpl;
        this.currentInventory.put(ItemType.BOOK, Lists.newArrayList(new BaseInventory(UUID.randomUUID(), ItemType.BOOK,
                        "random", Status.AVAILABLE, null, null, null),
                new BaseInventory(UUID.randomUUID(), ItemType.BOOK,
                        "random due ", Status.LOANED, null, LocalDate.of(2023, 9, 20), null)));
    }

    @Override
    public InventoryDTO getCurrentInventory() {
        List<String> titles = Lists.newArrayList();
        List<BaseInventory> bookInventories = Lists.newArrayList();
        currentInventory.values().forEach(bookInventories::addAll);

        titles.addAll(bookInventories.stream()
                .filter(baseInventory -> baseInventory.getStatus().equals(Status.AVAILABLE))
                .map(BaseInventory::getTitle)
                .toList());


        return new InventoryDTO(titles);
    }

    @Override
    public OverDueItemDTO getOverdueInventory() {
        List<BaseInventory> bookInventories = Lists.newArrayList();
        currentInventory.values().forEach(bookInventories::addAll);

        List<BaseInventory> loanedInventory = bookInventories
                .stream()
                .filter(item -> item.getStatus().equals(Status.LOANED))
                .filter(item -> item.getDueDate().isBefore(LocalDate.now()))
                .toList();

        return mapToOVerDueItemDTO(loanedInventory);
    }

    @Override
    public ItemAvailabilityDTO getItemAvailability(String itemType, String name) {
        if (currentInventory.containsKey(ItemType.valueOf(itemType))) {
            List<BaseInventory> bookInventories = currentInventory.get(ItemType.valueOf(itemType));
            List<BaseInventory> availableItems = bookInventories
                    .stream()
                    .filter(item -> item.getTitle().equals(name))
                    .filter(item -> item.getStatus().equals(Status.AVAILABLE))
                    .toList();
            return new ItemAvailabilityDTO(name, availableItems.size());
        }
        return new ItemAvailabilityDTO(name, 0);
    }

    @Override
    public BorrowItemDTO borrowItem(String itemType, String title, String userId) {
        BorrowItemDTO borrowItemDTO = new BorrowItemDTO();

        if (currentInventory.containsKey(ItemType.valueOf(itemType))) {
            List<BaseInventory> bookInventories = currentInventory.get(ItemType.valueOf(itemType));

            bookInventories
                    .stream()
                    .filter(item -> item.getTitle().equals(title))
                    .filter(item -> item.getStatus().equals(Status.AVAILABLE))
                    .toList().stream()
                    .findFirst()
                    .ifPresent(item -> {
                                item.setStatus(Status.LOANED);
                                item.setLoanDate(LocalDate.now());
                                item.setDueDate(LocalDate.now().plusDays(7));
                                item.setUserId(userId);

                                ledgerService.updateUserBorrowings(userId, item);

                                borrowItemDTO.setDueDate(item.getDueDate());
                                borrowItemDTO.setItemType(ItemType.valueOf(itemType));
                                borrowItemDTO.setUserId(userId);
                                borrowItemDTO.setStatus(Status.LOANED);
                                borrowItemDTO.setName(title);
                            }
                    );
        }

        return borrowItemDTO;
    }

    @Override
    public BorrowItemDTO returnItem(String itemType, String title, String userId) {
        BorrowItemDTO borrowItemDTO = new BorrowItemDTO();

        if (currentInventory.containsKey(ItemType.valueOf(itemType))) {
            List<BaseInventory> bookInventories = currentInventory.get(ItemType.valueOf(itemType));

            bookInventories
                    .stream()
                    .filter(item -> item.getTitle().equals(title))
                    .filter(item -> item.getStatus().equals(Status.LOANED))
                    .toList().stream()
                    .findFirst()
                    .ifPresent(item -> {
                                item.setStatus(Status.AVAILABLE);
                                item.setDueDate(null);

                                ledgerService.updateUserBorrowings(userId, item);

                                borrowItemDTO.setDueDate(item.getDueDate());
                                borrowItemDTO.setItemType(ItemType.valueOf(itemType));
                                borrowItemDTO.setUserId(userId);
                                borrowItemDTO.setStatus(Status.RETURNED);
                                borrowItemDTO.setName(title);
                            }
                    );
        }

        return borrowItemDTO;
    }

    private OverDueItemDTO mapToOVerDueItemDTO(List<BaseInventory> loanedInventory) {
        List<OverDueItem> overDueItems = Optional.of(loanedInventory)
                .filter(CollectionUtils::isNotEmpty)
                .map(list -> list.stream().map(this::mapToOverdue)
                        .collect(Collectors.toList())).orElseGet(Lists::newArrayList);

        return new OverDueItemDTO(overDueItems);
    }


    /**************UTILS***********************************************************************************************/

    private OverDueItem mapToOverdue(BaseInventory baseInventory) {
        return new OverDueItem(baseInventory.getTitle(), baseInventory.getItemType(), baseInventory.getDueDate());
    }
}
