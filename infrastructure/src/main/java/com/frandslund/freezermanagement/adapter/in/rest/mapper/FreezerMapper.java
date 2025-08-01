package com.frandslund.freezermanagement.infrastructure.adapter.in.rest.mapper;

import com.frandslund.freezermanagement.domain.model.freezer.Freezer;
import com.frandslund.freezermanagement.domain.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.domain.model.freezeritem.ItemData;
import com.frandslund.freezermanagement.domain.model.shelf.Shelf;
import com.frandslund.freezermanagement.infrastructure.adapter.in.rest.dto.FreezerItemDto;
import com.frandslund.freezermanagement.infrastructure.adapter.in.rest.dto.FreezerDto;
import com.frandslund.freezermanagement.infrastructure.adapter.in.rest.dto.ShelfDto;

import java.util.stream.Collectors;

/**
 * Utility class for mapping between domain models and DTOs.
 */
public final class FreezerMapper {

    private FreezerMapper() {
        // Prevent instantiation
    }

    /**
     * Maps a Freezer domain model to a FreezerDto.
     *
     * @param freezer The domain model.
     * @return The DTO representation.
     */
    public static FreezerDto toDto(Freezer freezer) {
        if (freezer == null) {
            return null;
        }

        return new FreezerDto(freezer.getFreezerId().id(), freezer.getAllShelves().stream().map(FreezerMapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Maps a Shelf domain model to a ShelfDto.
     *
     * @param shelf The domain model.
     * @return The DTO representation.
     */
    private static ShelfDto toDto(Shelf shelf) {
        if (shelf == null) {
            return null;
        }

        // Map ShelfId (UUID), shelf number, and recursively map all items to FreezerItemDtos
        return new ShelfDto(shelf.getShelfId().value(), shelf.getShelfNumber(), shelf.getFreezerItems().stream().map(FreezerMapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Maps a FreezerItem domain model to a FreezerItemDto.
     *
     * @param freezerItem The domain model.
     * @return The DTO representation.
     */
    private static FreezerItemDto toDto(FreezerItem freezerItem) {
        if (freezerItem == null) {
            return null;
        }

        // Map FreezerItemId (UUID) and the nested ItemData fields
        ItemData itemData = freezerItem.getItemData();
        return new FreezerItemDto(freezerItem.getFreezerItemId().value(), itemData.name(), itemData.quantity(), itemData.description(), itemData.dateAdded());
    }
}