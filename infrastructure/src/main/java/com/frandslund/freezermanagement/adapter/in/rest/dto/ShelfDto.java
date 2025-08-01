package com.frandslund.freezermanagement.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record ShelfDto(
        UUID id,
        int shelfNumber,
        List<FreezerItemDto> items
) {
}