package com.frandslund.freezermanagement.model.shelf;

import com.frandslund.freezermanagement.common.Entity;
import com.frandslund.freezermanagement.model.inventoryItem.InventoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shelf extends Entity {

    private final ShelfId shelfId;
    private final int shelfNumber;
    private final List<InventoryItem> inventoryItems;

    public Shelf(int shelfNumber) {
        this(shelfNumber, new ArrayList<>());
    }

    private Shelf(int shelfNumber, List<InventoryItem> inventoryItems) {
        this.shelfId = new ShelfId(UUID.randomUUID());
        this.shelfNumber = shelfNumber;
        this.inventoryItems = inventoryItems != null ? inventoryItems : new ArrayList<>();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public Shelf addInventoryItem(InventoryItem inventoryItem) {
        inventoryItems.add(inventoryItem);
        return this;
    }

    public List<InventoryItem> getInventoryItems() {
        // Ensure no modification of list outside class
        return List.copyOf(inventoryItems);
    }
}
