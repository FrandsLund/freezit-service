package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class FreezerEntity {

    @Id
    private UUID freezerId;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "freezerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<ShelfEntity> shelves = new HashSet<>();

    public UUID getFreezerId() {
        return freezerId;
    }

    public void setFreezerId(UUID freezerId) {
        this.freezerId = freezerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ShelfEntity> getShelfEntities() {
        return shelves;
    }
}
