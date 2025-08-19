package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.freezer.Shelf;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "ShelfWebModel", description = "Web model for a shelf inside a freezer")
public record ShelfWebModel(@Schema(examples = {"987e6543-e21a-32c1-b456-1234567890ab"}) UUID id,
                            @Schema(examples = {"1"}) int shelfNumber,
                            List<FreezerItemWebModel> items) {
    public static ShelfWebModel fromDomainModel(Shelf shelf) {
        List<FreezerItemWebModel> freezerItemWebModels = shelf.getFreezerItems().stream().map(FreezerItemWebModel::fromDomainModel).toList();
        return new ShelfWebModel(shelf.getShelfId().shelfId(), shelf.getShelfNumber(), freezerItemWebModels);
    }
}