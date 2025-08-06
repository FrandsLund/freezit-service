package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.freezer.Freezer;

import java.util.List;
import java.util.UUID;

public record FreezerWebModel(UUID freezerId, String freezerName, int userId, List<ShelfWebModel> shelves) {

    /**
     * Static factory method
     */
    public static FreezerWebModel fromDomainModel(Freezer freezer) {
        List<ShelfWebModel> shelfWebModels = freezer.getShelves().stream().map(ShelfWebModel::fromDomainModel).toList();
        return new FreezerWebModel(freezer.getFreezerId().freezerId(), freezer.getName(), freezer.getUserId().userId(), shelfWebModels);
    }
}

