package com.frandslund.freezermanagement.adapter.in.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CreateFreezerRequest", description = "Request for creating a freezer")
public record CreateFreezerRequest(@Schema(required = true, examples = {"123"}) int userId,
                                   @Schema(required = true, examples = {"Garage Freezer"}) String freezerName,
                                   @Schema(required = true, examples = {"3"}) int shelfQuantity) {
}