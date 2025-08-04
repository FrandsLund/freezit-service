package com.frandslund.freezermanagement.model.freezeritem;

import java.util.Objects;
import java.util.UUID;

public record FreezerItemId(UUID freezerItemId) {
    public FreezerItemId {
        Objects.requireNonNull(freezerItemId, "freezerItemId cannot be null");
    }

}
