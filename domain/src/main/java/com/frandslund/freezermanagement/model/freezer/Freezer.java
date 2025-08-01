package com.frandslund.freezermanagement.model.freezer;

import com.frandslund.freezermanagement.common.AggregateRoot;
import com.frandslund.freezermanagement.model.inventoryItem.InventoryItem;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.model.freezeritem.ItemData;
import com.frandslund.freezermanagement.model.shelf.Shelf;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Add validation
public class Freezer extends AggregateRoot {

    private final FreezerId freezerId;
    private final String name;
    private final Map<Integer, Shelf> shelves;

    public Freezer(String name, int shelfQuantity) {
        this(new FreezerId(UUID.randomUUID()), name, new ArrayList<>());

        // TODO: Is it an issue that this is places after the constructor
        if (shelfQuantity < 1) {
            throw new IllegalArgumentException(("'shelfQuantity must be greater than 0, current value: %s").formatted(shelfQuantity));
        }

        for (int i = 0; i < shelfQuantity; i++) {
            addShelf(i + 1);
        }
    }

//    private Freezer(FreezerId freezerId, String name, int shelfQuantity) {
//        this(freezerId, name, new ArrayList<>());
//    }

    private Freezer(FreezerId freezerId, String name, List<Shelf> shelves) {
        this.name = name;
        this.freezerId = freezerId;
        this.shelves = shelves.stream().collect(Collectors.toMap(Shelf::getShelfNumber, Function.identity()));
    }

    private void addShelf(int shelfNumber) {
        shelves.put(shelfNumber, new Shelf(shelfNumber));
    }

    public Freezer addInventoryItem(int shelfNumber, int quantity, String name, String description, Instant dateAdded) {
        FreezerItem freezerItem = new FreezerItem(new ItemData(name, description, dateAdded));
        InventoryItem inventoryItem = new InventoryItem(freezerItem, quantity);

        shelves.computeIfPresent(shelfNumber, (key, shelf) -> shelf.addInventoryItem(inventoryItem));
        return this;
    }

    public FreezerId getFreezerId() {
        return this.freezerId;
    }

    public List<Shelf> getAllShelves() {
        return shelves.values().stream().toList();
    }

    public String getName() {
        return name;
    }
}
