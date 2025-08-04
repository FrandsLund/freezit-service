package com.frandslund.freezermanagement.model.freezer;

import java.util.Objects;
import java.util.UUID;

/**
 * Wrapper class to avoid primitive obsession
 *
 * @param freezerId
 */
public record FreezerId(UUID freezerId) {

    public FreezerId {
        Objects.requireNonNull(freezerId, "FreezerId value cannot be null");
    }

    public FreezerId(String id) {
        this(UUID.fromString(id));
    }
}