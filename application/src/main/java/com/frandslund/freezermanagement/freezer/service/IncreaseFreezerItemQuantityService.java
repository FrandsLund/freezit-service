package com.frandslund.freezermanagement.freezer.service;

import com.frandslund.freezermanagement.freezer.port.in.IncreaseFreezerItemQuantityUseCase;
import com.frandslund.freezermanagement.freezer.port.out.messaging.DomainEventPublisherPort;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.FreezerItemId;
import com.frandslund.freezermanagement.model.freezer.exception.FreezerDoesNotExistException;
import jakarta.transaction.Transactional;

public class IncreaseFreezerItemQuantityService implements IncreaseFreezerItemQuantityUseCase {

    private final FreezerRepositoryPort freezerRepository;
    private final DomainEventPublisherPort eventPublisher;


    public IncreaseFreezerItemQuantityService(FreezerRepositoryPort freezerRepository, DomainEventPublisherPort eventPublisher) {
        this.freezerRepository = freezerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Freezer increaseFreezerItemQuantity(String freezerId, String freezerItemId, int quantity) {
        Freezer freezer = freezerRepository
                .findById(new FreezerId(freezerId))
                .orElseThrow(() -> new FreezerDoesNotExistException(freezerId));
        freezer.increaseFreezerItemQuantityBy(new FreezerItemId(freezerItemId), quantity);
        freezerRepository.save(freezer);
        freezer
                .getDomainEvents()
                .forEach(eventPublisher::publish);
        return freezer;
    }

}
