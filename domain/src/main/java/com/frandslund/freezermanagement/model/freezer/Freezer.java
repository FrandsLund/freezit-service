package com.frandslund.freezermanagement.model.freezer;

import com.frandslund.freezermanagement.common.AggregateRoot;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.model.freezeritem.ItemData;
import com.frandslund.freezermanagement.model.shelf.Shelf;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Add validation
public class Freezer extends AggregateRoot {

    private final FreezerId freezerId;
    private final UserId userId;
    private final String name;
    private final Map<Integer, Shelf> shelves;

    public Freezer(UserId userId, String name, int shelfQuantity) {
        this(new FreezerId(UUID.randomUUID()), userId, name, new ArrayList<>());

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

    private Freezer(FreezerId freezerId, UserId userId, String name, List<Shelf> shelves) {
        this.userId = userId;
        this.name = name;
        this.freezerId = freezerId;
        this.shelves = shelves.stream().collect(Collectors.toMap(Shelf::getShelfNumber, Function.identity()));
    }

    private void addShelf(int shelfNumber) {
        shelves.put(shelfNumber, new Shelf(shelfNumber));
    }

    public void addFreezerItem(int shelfNumber, int quantity, String name, String description, Instant dateAdded) {
        ItemData itemData = new ItemData(name, description, dateAdded);
        FreezerItem freezerItem = new FreezerItem(itemData, quantity);
        shelves.computeIfPresent(shelfNumber, (key, shelf) -> shelf.addFreezerItem(freezerItem));
    }

    public FreezerId getFreezerId() {
        return this.freezerId;
    }

    public List<Shelf> getShelves() {
        return shelves.values().stream().toList();
    }

    public String getName() {
        return name;
    }

    public UserId getUserId() {
        return userId;
    }
}
