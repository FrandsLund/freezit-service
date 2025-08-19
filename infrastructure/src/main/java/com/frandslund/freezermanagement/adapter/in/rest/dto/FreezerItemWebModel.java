package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.freezer.FreezerItem;
import com.frandslund.freezermanagement.model.freezer.ItemData;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;


@Schema(name = "FreezerItemWebModel", description = "Web model for an item stored in a freezer")
public record FreezerItemWebModel(@Schema(examples = {"111e2222-3333-4444-5555-666677778888"}) UUID id,
                                  @Schema(examples = {"Chicken breast"}) String name,
                                  @Schema(examples = {"4"}) int quantity,
                                  @Schema(examples = {"Organic, boneless"}) String description,
                                  @Schema(examples = {"2025-08-08T10:30:00Z"}) Instant dateAdded) {

    public static FreezerItemWebModel fromDomainModel(FreezerItem freezerItem) {
        ItemData itemData = freezerItem.getItemData();
        return new FreezerItemWebModel(freezerItem.getFreezerItemId().freezerItemId(), itemData.name(), freezerItem.getQuantity(), itemData.description(), itemData.dateAdded());
    }

}