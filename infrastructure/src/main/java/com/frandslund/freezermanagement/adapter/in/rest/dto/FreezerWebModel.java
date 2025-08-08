package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(name = "FreezerWebModel", description = "Web model for a freezer")
public record FreezerWebModel(@Schema(examples = {"123e4567-e89b-12d3-a456-426614174000"}) UUID freezerId,
                              @Schema(examples = {"Garage Freezer"}) String freezerName,
                              @Schema(examples = {"123"}) int userId, List<ShelfWebModel> shelves) {

    /**
     * Static factory method
     */
    public static FreezerWebModel fromDomainModel(Freezer freezer) {
        List<ShelfWebModel> shelfWebModels = freezer.getShelves().stream().map(ShelfWebModel::fromDomainModel).toList();
        return new FreezerWebModel(freezer.getFreezerId().freezerId(), freezer.getName(), freezer.getUserId().userId(), shelfWebModels);
    }
}