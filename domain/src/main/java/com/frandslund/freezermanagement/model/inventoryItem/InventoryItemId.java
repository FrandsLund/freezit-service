package com.frandslund.freezermanagement.model.inventoryItem;

import java.util.Objects;
import java.util.UUID;

public record InventoryItemId(UUID inventoryItemId) {
    public InventoryItemId {
        Objects.requireNonNull(inventoryItemId, "inventoryItemId cannot be null");
    }

}
