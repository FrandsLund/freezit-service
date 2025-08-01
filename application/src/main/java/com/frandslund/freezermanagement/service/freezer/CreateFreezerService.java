package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.security.InvalidParameterException;
import java.time.Instant;

// TODO: Add transactional, når der skal publiseres events

@ApplicationScoped
public class CreateFreezerService implements CreateFreezerUseCase {

    private final FreezerRepository freezerRepository;

    public CreateFreezerService(FreezerRepository freezerRepository) {
        this.freezerRepository = freezerRepository;
    }

    @Override
    public FreezerId createFreezer(String name, int shelfQuantity) {
        var freezer = new Freezer(name, shelfQuantity);
        freezerRepository.save(freezer);
        return freezer.getFreezerId();
    }
}
