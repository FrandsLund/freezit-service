package com.frandslund.freezermanagement.service.freezeritem;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;

import java.util.NoSuchElementException;

public class AddFreezerItemService implements AddFreezerItemUseCase {

    private final FreezerRepository freezerRepository;

    public AddFreezerItemService(FreezerRepository freezerRepository) {
        this.freezerRepository = freezerRepository;
    }

    @Override
    public Freezer addFreezerItemUseCase(FreezerId freezerId, int shelfNumber, String name, int quantity, String description) {
        var freezer = freezerRepository.findById(freezerId).orElseThrow(() -> new NoSuchElementException("Freezer with ID %s not found.".formatted(freezerId)));
        freezer.addFreezerItem(shelfNumber, quantity, name, description);
        freezerRepository.save(freezer);
        return freezer;
    }
}
