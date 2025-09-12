package com.frandslund.freezermanagement.model.freezer;

import com.frandslund.freezermanagement.model.freezer.exception.InvalidFreezerItemNameException;

import java.time.Instant;


// TODO: Add validation
// TODO: dateAdded should be moved to FreezerItem
public record ItemData(String name, String description, Instant dateAdded) {

    public ItemData(String name, String description) {
        this(name, description, Instant.now());

        if (name.isEmpty()) {
            throw new InvalidFreezerItemNameException("'name' cannot be empty");
        }
    }
}
