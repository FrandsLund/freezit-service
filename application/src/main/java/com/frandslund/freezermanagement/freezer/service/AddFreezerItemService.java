package com.frandslund.freezermanagement.freezer.service;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.freezer.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.freezer.port.out.messaging.DomainEventPublisherPort;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

public class AddFreezerItemService implements AddFreezerItemUseCase {
    private final FreezerRepositoryPort freezerRepositoryPort;
    private final DomainEventPublisherPort eventPublisher;

    public AddFreezerItemService(FreezerRepositoryPort freezerRepositoryPort, DomainEventPublisherPort eventPublisher) {
        this.freezerRepositoryPort = freezerRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Freezer addFreezerItemUseCase(FreezerId freezerId, int shelfNumber, String name, int quantity, String description) throws NoSuchElementException {
        var freezer = freezerRepositoryPort
                .findById(freezerId).orElseThrow(() -> new NoSuchElementException("Freezer with ID %s not found.".formatted(freezerId)));
        freezer.addFreezerItem(shelfNumber, quantity, name, description);
        freezerRepositoryPort.save(freezer);
        freezer.getDomainEvents().forEach(eventPublisher::publish);
        return freezer;
    }
}