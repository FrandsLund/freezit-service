package com.frandslund.freezermanagement.adapter.in.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "UpdateFreezerItemQuantityRequest", description = "Request for increasing quantity of a freezer item")
public record UpdateFreezerItemQuantityRequest(
        @Schema(required = true, examples = "123e4567-e89b-12d3-a456-426614174000") UUID freezerItemId,
        @Schema(required = true, examples = "2") int quantity) {
}