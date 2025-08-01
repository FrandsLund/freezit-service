package com.frandslund.freezermanagement.domain.model.shelf;

import java.util.Objects;
import java.util.UUID;

public record ShelfId(UUID value) {

    public ShelfId {
        Objects.requireNonNull(value, "ShelfId value cannot be null");
    }

    public ShelfId(String value) {
        this(UUID.fromString(value));
    }
}