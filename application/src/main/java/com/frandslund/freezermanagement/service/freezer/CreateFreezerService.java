package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;

// TODO: Add transactional, når der skal publiseres events

public class CreateFreezerService implements CreateFreezerUseCase {

    private final FreezerRepository freezerRepository;

    public CreateFreezerService(FreezerRepository freezerRepository) {
        this.freezerRepository = freezerRepository;
    }

    @Override
    public Freezer createFreezer(int userId, String freezerName, int shelfQuantity) {
        var freezer = new Freezer(new UserId(userId), freezerName, shelfQuantity);
        freezerRepository.save(freezer);
        return freezer;
    }
}
