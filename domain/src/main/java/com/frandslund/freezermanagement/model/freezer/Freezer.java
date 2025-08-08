package com.frandslund.freezermanagement.model.freezer;

import com.frandslund.freezermanagement.common.AggregateRoot;
import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.event.FreezerAddedEvent;
import com.frandslund.freezermanagement.event.FreezerItemAddedEvent;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import com.frandslund.freezermanagement.model.freezeritem.ItemData;
import com.frandslund.freezermanagement.model.shelf.Shelf;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// TODO: Add validation
public class Freezer extends AggregateRoot {

    private final FreezerId freezerId;
    private final UserId userId;
    private final String name;
    private final Map<Integer, Shelf> shelves;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Freezer(UserId userId, String name, int shelfQuantity) {
        this(new FreezerId(UUID.randomUUID()), userId, name, new ArrayList<>());

        // TODO: Is it an issue that this is places after the constructor
        if (shelfQuantity < 1) {
            throw new IllegalArgumentException(("'shelfQuantity must be greater than 0, current value: %s").formatted(shelfQuantity));
        }

        for (int i = 0; i < shelfQuantity; i++) {
            addShelf(i + 1);
        }

        domainEvents.add(new FreezerAddedEvent(freezerId));
    }

    public Freezer(FreezerId freezerId, UserId userId, String name, List<Shelf> shelves) {
        this.userId = userId;
        this.name = name;
        this.freezerId = freezerId;
        this.shelves = shelves.stream().collect(Collectors.toMap(Shelf::getShelfNumber, Function.identity()));
    }

    private void addShelf(int shelfNumber) {
        shelves.put(shelfNumber, new Shelf(shelfNumber));
    }

    public void addFreezerItem(int shelfNumber, int quantity, String name, String description) {
        ItemData itemData = new ItemData(name, description);
        FreezerItem freezerItem = new FreezerItem(itemData, quantity);
        shelves.computeIfPresent(shelfNumber, (key, shelf) -> {
            shelf.addFreezerItem(freezerItem);
            domainEvents.add(new FreezerItemAddedEvent(freezerId, freezerItem.getFreezerItemId()));
            return shelf;
        });

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

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Freezer freezer = (Freezer) o;
        return Objects.equals(freezerId, freezer.freezerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freezerId);
    }

    @Override
    public String toString() {
        return "Freezer{" +
                "freezerId=" + freezerId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", shelves=" + shelves +
                '}';
    }
}
