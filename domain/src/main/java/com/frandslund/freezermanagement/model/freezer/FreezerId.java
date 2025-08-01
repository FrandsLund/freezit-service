package com.frandslund.freezermanagement.domain.model.freezer;

import java.util.Objects;
import java.util.UUID;

public record FreezerId(UUID id) {

    public FreezerId {
        Objects.requireNonNull(id, "FreezerId value cannot be null");
    }

    public FreezerId(String id) {
        this(UUID.fromString(id));
    }
}