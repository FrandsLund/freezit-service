package com.frandslund.freezermanagement.adapter.in.rest.dto;

import com.frandslund.freezermanagement.model.shelf.Shelf;

import java.util.List;
import java.util.UUID;

public record ShelfWebModel(UUID id, int shelfNumber, List<FreezerItemWebModel> items) {
    public static ShelfWebModel fromDomainModel(Shelf shelf) {
        List<FreezerItemWebModel> freezerItemWebModels = shelf.getFreezerItems().stream().map(FreezerItemWebModel::fromDomainModel).toList();
        return new ShelfWebModel(shelf.getShelfId().shelfId(), shelf.getShelfNumber(), freezerItemWebModels);
    }
}