package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.domain.model.freezer.Freezer;
import com.frandslund.freezermanagement.domain.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.FreezerService;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.security.InvalidParameterException;
import java.time.Instant;

// TODO: Add transactional, når der skal publiseres events

@ApplicationScoped
public class FreezerApplicationService implements FreezerService {

    @Inject
    FreezerRepository freezerRepository;

    @Override
    public FreezerId createFreezer(String name) {
        var freezer = new Freezer(name);
        freezerRepository.save(freezer);
        return freezer.getFreezerId();
    }

    @Override
    public void addShelf(FreezerId freezerId, int shelfNumber) {
        var freezer = getFreezer(freezerId);
        freezer.addShelf(shelfNumber);
        freezerRepository.save(freezer);
    }

    @Override
    public void addFreezerItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded) {
        var freezer = getFreezer(freezerId);
        freezer.addFreezerItem(shelfNumber, name, quantity, description, dateAdded);
        freezerRepository.save(freezer);
    }

    @Override
    public Freezer getFreezer(FreezerId freezerId) {
        return freezerRepository.findById(freezerId).orElseThrow(() -> new InvalidParameterException("Freezer with ID " + freezerId.id() + " not found."));
    }
}
