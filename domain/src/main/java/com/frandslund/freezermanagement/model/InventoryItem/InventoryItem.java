package com.frandslund.freezermanagement.model.InventoryItem;

import com.frandslund.freezermanagement.common.Entity;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;

import java.util.UUID;

// TODO: Consider renaming

/**
 * The model in this class is called a “Rich Domain Model” – in contrast to an “Anemic Domain Model,” where the model classes contain only fields, getters, and setters, and the business logic is implemented in service classes.
 */
public class InventoryItem extends Entity {

    private final InventoryItemId inventoryItemId;

    private final FreezerItem freezerItem;
    private int quantity;

    public InventoryItem(FreezerItem freezerItem, int quantity) {
        validateQuantity(quantity);

        this.inventoryItemId = new InventoryItemId(UUID.randomUUID());
        this.freezerItem = freezerItem;
        this.quantity = quantity;
    }

    public void increaseQuantityBy(int value) {
        validatePositiveNumber(value);
        quantity += value;
    }

    private InventoryItem(InventoryItemId inventoryItemId, FreezerItem freezerItem, int quantity) {
        validateQuantity(quantity);

        this.inventoryItemId = inventoryItemId;
        this.freezerItem = freezerItem;
        this.quantity = quantity;
    }

    public void decreaseQuantityBy(int value) {
        validatePositiveNumber(value);
        quantity -= value;
        validateQuantity(quantity);
    }

    private void validatePositiveNumber(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("'value' must be positive");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("'quantity' must not be negative");
        }
    }
}

