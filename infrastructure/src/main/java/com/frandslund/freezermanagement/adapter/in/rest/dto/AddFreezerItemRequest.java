package com.frandslund.freezermanagement.adapter.in.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "AddFreezerItemRequest", description = "Request for adding a Freezer Item")

public record AddFreezerItemRequest(@Schema(required = true, examples = {"Pork belly"}) String itemName,
                                    @Schema(required = true, examples = {"2"}) int quantity,
                                    @Schema(required = true, examples = {"Very fatty"}) String description) {
}
