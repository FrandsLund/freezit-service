package com.frandslund.freezermanagement.domain.model;

import com.frandslund.freezermanagement.domain.common.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shelf extends Entity {

    private final UUID shelfId;
    private final int shelfNumber;
    private final List<FreezerItem> freezerItems;

    protected Shelf(int shelfNumber) {
        this(shelfNumber, new ArrayList<>());
    }

    protected Shelf(int shelfNumber, List<FreezerItem> freezerItems) {
        this.shelfId = UUID.randomUUID();
        this.shelfNumber = shelfNumber;
        this.freezerItems = freezerItems != null ? freezerItems : new ArrayList<>();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public Shelf addFreezerItem(FreezerItem freezerItem) {
        freezerItems.add(freezerItem);
        return this;
    }

}
