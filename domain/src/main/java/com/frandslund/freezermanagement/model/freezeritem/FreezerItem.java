package com.frandslund.freezermanagement.model.freezeritem;

import com.frandslund.freezermanagement.common.Entity;

import java.util.UUID;

// TODO: Add validation

// TODO: Consider if this could be a value object. If true, it's just the ItemData record that should be renamed to FreezerItem

public class FreezerItem extends Entity {
    private final FreezerItemId freezerItemId;
    private final ItemData itemData;

    public FreezerItem(ItemData itemData) {
        this.freezerItemId = new FreezerItemId(UUID.randomUUID());
        this.itemData = itemData;
    }

    public FreezerItemId getFreezerItemId() {
        return freezerItemId;
    }

    public ItemData getItemData() {
        return itemData;
    }
}
