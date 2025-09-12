package com.frandslund.freezermanagement.freezer.service;

import com.frandslund.freezermanagement.freezer.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.freezer.port.out.messaging.DomainEventPublisherPort;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.exception.FreezerNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddFreezerItemService implements AddFreezerItemUseCase {
    private final FreezerRepositoryPort freezerRepositoryPort;
    private final DomainEventPublisherPort eventPublisher;

    private final static Logger LOG = LoggerFactory.getLogger(AddFreezerItemService.class);

    public AddFreezerItemService(FreezerRepositoryPort freezerRepositoryPort, DomainEventPublisherPort eventPublisher) {
        this.freezerRepositoryPort = freezerRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    // TODO: Some testing has been done here, that shuld be removed
    @Override
    @Transactional
    public Freezer addFreezerItemUseCase(FreezerId freezerId, int shelfNumber, String name, int quantity, String description) {
        var freezer = freezerRepositoryPort
                .findById(freezerId)
                .orElseThrow(() -> new FreezerNotFoundException(freezerId));
        freezer.addFreezerItem(shelfNumber, quantity, name, description);
        try {
            freezerRepositoryPort.save(freezer);
            freezer
                    .getDomainEvents()
                    .forEach(eventPublisher::publish);
        } catch (RuntimeException e) {
            LOG.error("TEST LOG", e);
        }
        return freezer;
    }
}