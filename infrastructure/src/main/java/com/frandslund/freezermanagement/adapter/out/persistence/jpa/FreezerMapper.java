package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItemId;
import com.frandslund.freezermanagement.model.freezeritem.ItemData;
import com.frandslund.freezermanagement.model.shelf.Shelf;

final class FreezerMapper {

    private FreezerMapper() {
    }

    static FreezerEntity toFreezerEntity(Freezer freezer) {
        FreezerEntity freezerEntity = new FreezerEntity();
        freezerEntity.setFreezerId(freezer.getFreezerId().freezerId());
        freezerEntity.setName(freezer.getName());
        freezerEntity.setUserId(freezer.getUserId().userId());
        var shelfEntities = freezer.getShelves().stream().map(shelf -> toShelfEntity(shelf, freezerEntity)).toList();
        freezerEntity.getShelfEntities().addAll(shelfEntities);
        return freezerEntity;

    }

    static Freezer toFreezer(FreezerEntity freezerEntity) {
        var shelves = freezerEntity.getShelfEntities().stream().map(FreezerMapper::toShelf).toList();
        return new Freezer(freezerEntity.getFreezerId(), freezerEntity.getUserId(), freezerEntity.getName(), shelves);
    }

    private static Shelf toShelf(ShelfEntity shelfEntity) {
        var freezerItems = shelfEntity.getFreezerItemEntities().stream().map(FreezerMapper::toFreezerItem).toList();
        return new Shelf(shelfEntity.getShelfNumber(), freezerItems);
    }

    private static FreezerItem toFreezerItem(FreezerItemEntity freezerItemEntity) {
        var itemData = new ItemData(freezerItemEntity.getName(), freezerItemEntity.getDescription(), freezerItemEntity.getDateAdded());
        var freezerItemId = new FreezerItemId(freezerItemEntity.getFreezerItemId());
        return new FreezerItem(freezerItemId, itemData, freezerItemEntity.getQuantity());
    }

    private static FreezerItemEntity toFreezerItemEntity(FreezerItem freezerItem, ShelfEntity shelfEntity) {
        FreezerItemEntity freezerItemEntity = new FreezerItemEntity();
        freezerItemEntity.setFreezerItemId(freezerItem.getFreezerItemId().freezerItemId());
        freezerItemEntity.setDateAdded(freezerItem.getItemData().dateAdded());
        freezerItemEntity.setName(freezerItem.getItemData().name());
        freezerItemEntity.setDescription(freezerItem.getItemData().description());
        freezerItemEntity.setShelfEntity(shelfEntity);
        return freezerItemEntity;
    }

    private static ShelfEntity toShelfEntity(Shelf shelf, FreezerEntity freezerEntity) {
        ShelfEntity shelfEntity = new ShelfEntity();
        shelfEntity.setShelfId(shelf.getShelfId().shelfId());
        shelfEntity.setShelfNumber(shelf.getShelfNumber());
        shelfEntity.setFreezerEntity(freezerEntity);
        var freezerItemEntities = shelf.getFreezerItems().stream().map(freezerItem -> toFreezerItemEntity(freezerItem, shelfEntity)).toList();
        shelfEntity.getFreezerItemEntities().addAll(freezerItemEntities);
        return shelfEntity;
    }

}
