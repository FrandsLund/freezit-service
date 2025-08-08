package com.frandslund.freezermanagement.service.freezeritem;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.out.event.DomainEventPublisher;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

public class AddFreezerItemService implements AddFreezerItemUseCase {
    private final FreezerRepository freezerRepository;
    private final DomainEventPublisher eventPublisher;

    public AddFreezerItemService(FreezerRepository freezerRepository, DomainEventPublisher eventPublisher) {
        this.freezerRepository = freezerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Freezer addFreezerItemUseCase(FreezerId freezerId, int shelfNumber, String name, int quantity, String description) throws NoSuchElementException {
        var freezer = freezerRepository.findById(freezerId).orElseThrow(() -> new NoSuchElementException("Freezer with ID %s not found.".formatted(freezerId)));
        freezer.addFreezerItem(shelfNumber, quantity, name, description);
        freezerRepository.save(freezer);
        freezer.getDomainEvents().forEach(eventPublisher::publish);
        return freezer;
    }
}
