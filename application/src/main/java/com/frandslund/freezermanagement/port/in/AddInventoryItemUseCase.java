package com.frandslund.freezermanagement.port.in;

import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.time.Instant;

public interface AddInventoryItemUseCase {

    void addInventoryItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded);

}
