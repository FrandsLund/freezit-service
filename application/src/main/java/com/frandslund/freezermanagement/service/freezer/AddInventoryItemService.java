package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.AddInventoryItemUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.security.InvalidParameterException;
import java.time.Instant;

@ApplicationScoped
public class AddInventoryItemService implements AddInventoryItemUseCase {

    @Inject
    FreezerRepository freezerRepository;

    @Override
    public void addInventoryItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded) {
        var freezer = freezerRepository.findById(freezerId).orElseThrow(() -> new InvalidParameterException("Freezer with ID " + freezerId.freezerId().toString() + " not found."));
        freezer.addInventoryItem(shelfNumber, quantity, name, description, dateAdded);
        freezerRepository.save(freezer);
    }
}
