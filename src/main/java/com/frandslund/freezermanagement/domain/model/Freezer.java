package com.frandslund.freezermanagement.domain.model;

import com.frandslund.freezermanagement.domain.common.AggregateRoot;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Add validation
public class Freezer extends AggregateRoot {

    public final UUID freezerId;
    private final Map<Integer, Shelf> shelves;

    public Freezer(UUID freezerId, List<Shelf> shelves) {
        this.freezerId = freezerId;
        this.shelves = shelves.stream().collect(Collectors.toMap(Shelf::getShelfNumber, Function.identity()));
    }

    public Freezer addShelf(int shelfNumber) {
        // TODO: Use putIfAbsent() for validation
        shelves.put(shelfNumber, new Shelf(shelfNumber));
        return this;
    }

    public Freezer addFreezerItem(int shelfNumber, String name, int quantity, String description, Instant dateAdded) {
        shelves.computeIfPresent(shelfNumber, (key, shelf) -> shelf.addFreezerItem(new FreezerItem(new ItemData(name, quantity, description, dateAdded))));
        return this;
    }

    public List<Shelf> getAllShelves() {
        return shelves.values().stream().toList();
    }


}
