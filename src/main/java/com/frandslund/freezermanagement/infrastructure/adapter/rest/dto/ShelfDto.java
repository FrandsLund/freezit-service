package com.frandslund.freezermanagement.infrastructure.adapter.rest.dto;

import java.util.List;
import java.util.UUID;

public record ShelfDto(
        UUID id,
        int shelfNumber,
        List<FreezerItemDto> items
) {
}