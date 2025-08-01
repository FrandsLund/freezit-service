package com.frandslund.freezermanagement.model.InventoryItem;

import com.frandslund.freezermanagement.common.Entity;

/**
 * The model in this class is called a “Rich Domain Model” – in contrast to an “Anemic Domain Model,” where the model classes contain only fields, getters, and setters, and the business logic is implemented in service classes.
 */
public class InventoryItem extends Entity {

    private final InventoryItemId inventoryItemId;
    private int quantity;

    public InventoryItem(InventoryItemId inventoryItemId, int quantity) {
        validateQuantity(quantity);

        this.inventoryItemId = inventoryItemId;
        this.quantity = quantity;
    }

    public void increaseQuantityBy(int value) {
        validatePositiveNumber(value);
        quantity += value;
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

