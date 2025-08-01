package com.frandslund.freezermanagement.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record FreezerDto(
        UUID id,
        List<ShelfDto> shelves
) {
}