package com.library.inventory.services;

import com.library.inventory.models.BaseInventory;
import com.library.inventory.models.ItemType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InitialInventoryServiceImpl {

    private final Map<ItemType, List<BaseInventory>> currentInventory = new ConcurrentHashMap<>();


    void createInitialInventory() {
        /*
         * This method will be responsible for creating the inventory via uploading the csv/could provide endpoints to create inventory in prod.
         *
         * */
    }

    void updateInventory(BaseInventory baseInventory) {

        /*update inventory on chnages*/

    }

}
