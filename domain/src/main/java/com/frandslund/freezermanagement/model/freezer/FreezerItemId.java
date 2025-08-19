package com.frandslund.freezermanagement.model.freezer;

import java.util.Objects;
import java.util.UUID;

public record FreezerItemId(UUID freezerItemId) {
    public FreezerItemId {
        Objects.requireNonNull(freezerItemId, "freezerItemId cannot be null");
    }

    public FreezerItemId(String id) {
        this(UUID.fromString(id));
    }

}
