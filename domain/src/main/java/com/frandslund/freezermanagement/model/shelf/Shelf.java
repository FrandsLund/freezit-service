package com.frandslund.freezermanagement.model.shelf;

import com.frandslund.freezermanagement.common.Entity;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shelf extends Entity {

    private final ShelfId shelfId;
    private final int shelfNumber;
    private final List<FreezerItem> freezerItems;

    public Shelf(int shelfNumber) {
        this(shelfNumber, new ArrayList<>());
    }

    public Shelf(int shelfNumber, List<FreezerItem> freezerItems) {
        this.shelfId = new ShelfId(UUID.randomUUID());
        this.shelfNumber = shelfNumber;
        this.freezerItems = freezerItems != null ? new ArrayList<>(freezerItems) : new ArrayList<>();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public Shelf addFreezerItem(FreezerItem freezerItem) {
        freezerItems.add(freezerItem);
        return this;
    }

    public List<FreezerItem> getFreezerItems() {
        // Ensure no modification of list outside class
        return List.copyOf(freezerItems);
    }

    public ShelfId getShelfId() {
        return shelfId;
    }
}
