package com.frandslund.freezermanagement.domain.model;

import com.frandslund.freezermanagement.domain.common.Entity;

import java.util.UUID;

// TODO: Add validation
public class FreezerItem extends Entity {
    private final UUID freezerItemId;
    private final ItemData itemData;

    public FreezerItem(ItemData itemData) {
        this.freezerItemId = UUID.randomUUID();
        this.itemData = itemData;
    }

}
