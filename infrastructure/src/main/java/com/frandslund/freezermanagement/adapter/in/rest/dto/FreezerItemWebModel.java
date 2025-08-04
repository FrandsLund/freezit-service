package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.model.freezeritem.ItemData;

import java.time.Instant;
import java.util.UUID;

public record FreezerItemWebModel(UUID id, String name, int quantity, String description, Instant dateAdded) {

    public static FreezerItemWebModel fromDomainModel(FreezerItem freezerItem) {
        ItemData itemData = freezerItem.getItemData();
        return new FreezerItemWebModel(freezerItem.getFreezerItemId().freezerItemId(), itemData.name(), freezerItem.getQuantity(), itemData.description(), itemData.dateAdded());
    }

}
