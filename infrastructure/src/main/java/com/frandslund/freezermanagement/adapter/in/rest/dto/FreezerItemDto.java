package com.frandslund.freezermanagement.infrastructure.adapter.in.rest.dto;

import java.time.Instant;
import java.util.UUID;

public record FreezerItemDto(
        UUID id,
        String name,
        int quantity,
        String description,
        Instant dateAdded
) {
}
