package com.frandslund.freezermanagement.domain.model;

import com.frandslund.freezermanagement.domain.common.AggregateRoot;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Add validation
public class Freezer extends AggregateRoot {

    private final String name;
    private final FreezerId freezerId;
    private final Map<Integer, Shelf> shelves;

    public Freezer(String name) {
        this(name, new FreezerId(UUID.randomUUID()));
    }

    public Freezer(String name, FreezerId freezerId) {
        this(name, freezerId, new ArrayList<>());
    }

    public Freezer(String name, FreezerId freezerId, List<Shelf> shelves) {
        this.name = name;
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

    public FreezerId getFreezerId() {
        return this.freezerId;
    }

    public List<Shelf> getAllShelves() {
        return shelves.values().stream().toList();
    }


}
