package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.out.event.DomainEventPublisher;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.transaction.Transactional;

public class CreateFreezerService implements CreateFreezerUseCase {

    private final FreezerRepository freezerRepository;
    private final DomainEventPublisher eventPublisher;

    public CreateFreezerService(FreezerRepository freezerRepository, DomainEventPublisher eventPublisher) {
        this.freezerRepository = freezerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Freezer createFreezer(int userId, String freezerName, int shelfQuantity) {
        var freezer = new Freezer(new UserId(userId), freezerName, shelfQuantity);
        freezerRepository.save(freezer);
        freezer.getDomainEvents().forEach(eventPublisher::publish);
        return freezer;
    }
}
