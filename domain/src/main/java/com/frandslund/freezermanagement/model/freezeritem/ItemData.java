package com.frandslund.freezermanagement.model.freezeritem;

import java.time.Instant;


// TODO: Add validation
// TODO: dateAdded should be moved to InventoryItem
public record ItemData(String name, String description, Instant dateAdded) {
}
