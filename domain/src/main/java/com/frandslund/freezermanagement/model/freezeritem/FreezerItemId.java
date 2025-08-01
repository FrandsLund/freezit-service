package com.frandslund.freezermanagement.domain.model.freezeritem;

import java.util.Objects;
import java.util.UUID;

public record FreezerItemId(UUID value) {

    public FreezerItemId {
        Objects.requireNonNull(value, "FreezerItemId value cannot be null");
    }

    public FreezerItemId(String value) {
        this(UUID.fromString(value));
    }
}