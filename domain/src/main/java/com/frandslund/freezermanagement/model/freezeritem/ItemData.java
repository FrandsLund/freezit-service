package com.frandslund.freezermanagement.model.freezeritem;

import java.time.Instant;


// TODO: Add validation
// TODO: dateAdded should be moved to FreezerItem
public record ItemData(String name, String description, Instant dateAdded) {

    public ItemData(String name, String description) {
        this(name, description, Instant.now());

        if (name.equals("")) {
            throw new IllegalArgumentException("'name' cannot be empty");
        }
    }
}
