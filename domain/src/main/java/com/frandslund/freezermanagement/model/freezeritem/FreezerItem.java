package com.frandslund.freezermanagement.domain.model.freezeritem;

import com.frandslund.freezermanagement.domain.common.Entity;

import java.util.UUID;

// TODO: Add validation
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
