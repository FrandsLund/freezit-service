package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Instant;
import java.util.UUID;

@Entity
public class FreezerItemEntity {

    @Id
    private UUID freezerItemId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant dateAdded;

    @ManyToOne
    private ShelfEntity shelfEntity;

    private String description;


    public UUID getFreezerItemId() {
        return freezerItemId;
    }

    public void setFreezerItemId(UUID freezerItemId) {
        this.freezerItemId = freezerItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Instant dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public ShelfEntity getShelfEntity() {
        return shelfEntity;
    }

    public void setShelfEntity(ShelfEntity shelfEntity) {
        this.shelfEntity = shelfEntity;
    }
}
