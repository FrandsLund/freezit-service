package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class ShelfEntity {

    @Id
    private UUID shelfId;

    @Column(nullable = false)
    private int shelfNumber;

    @ManyToOne
    private FreezerEntity freezerEntity;

    @OneToMany(mappedBy = "shelfEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<FreezerItemEntity> freezerItems = new HashSet<>();

    public UUID getShelfId() {
        return shelfId;
    }

    public void setShelfId(UUID shelfId) {
        this.shelfId = shelfId;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public FreezerEntity getFreezerEntity() {
        return freezerEntity;
    }

    public void setFreezerEntity(FreezerEntity freezerEntity) {
        this.freezerEntity = freezerEntity;
    }

    public Set<FreezerItemEntity> getFreezerItemEntities() {
        return freezerItems;
    }
}
