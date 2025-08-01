package com.frandslund.freezermanagement.model.shelf;

import java.util.Objects;
import java.util.UUID;

public record ShelfId(UUID shelfId) {

    public ShelfId {
        Objects.requireNonNull(shelfId, "ShelfId cannot be null");
    }

    public ShelfId(String shelfId) {
        this(UUID.fromString(shelfId));
    }
}