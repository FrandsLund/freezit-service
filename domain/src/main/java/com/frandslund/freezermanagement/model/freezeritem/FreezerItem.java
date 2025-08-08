package com.frandslund.freezermanagement.model.freezeritem;

import com.frandslund.freezermanagement.common.Entity;

import java.util.UUID;

/**
 * The model in this class is called a “Rich Domain Model” – in contrast to an “Anemic Domain Model,” where the model classes contain only fields, getters, and setters, and the business logic is implemented in service classes.
 */
public class FreezerItem extends Entity {

    private final FreezerItemId freezerItemId;
    private final ItemData itemData;
    private int quantity;

    public FreezerItem(ItemData itemData, int quantity) {
        validateQuantity(quantity);

        this.freezerItemId = new FreezerItemId(UUID.randomUUID());
        this.itemData = itemData;
        this.quantity = quantity;
    }

    public void increaseQuantityBy(int value) {
        validatePositiveInput(value);
        quantity += value;
    }

    public void decreaseQuantityBy(int value) {
        validatePositiveInput(value);
        quantity -= value;
        validateQuantity(quantity);
    }

    public FreezerItemId getFreezerItemId() {
        return freezerItemId;
    }

    public ItemData getItemData() {
        return itemData;
    }

    public int getQuantity() {
        return quantity;
    }

    public FreezerItem(FreezerItemId freezerItemId, ItemData itemData, int quantity) {
        validateQuantity(quantity);

        this.freezerItemId = freezerItemId;
        this.itemData = itemData;
        this.quantity = quantity;
    }

    private void validatePositiveInput(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("'value' must be positive");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("'quantity' must not be negative");
        }
    }

    @Override
    public String toString() {
        return "FreezerItem{" +
                "freezerItemId=" + freezerItemId +
                ", itemData=" + itemData +
                ", quantity=" + quantity +
                '}';
    }
}

