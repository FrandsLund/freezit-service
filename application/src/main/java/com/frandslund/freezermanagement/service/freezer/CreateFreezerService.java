package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.port.in.freezer.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.out.event.freezer.DomainEventPublisherPort;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepositoryPort;
import jakarta.transaction.Transactional;

public class CreateFreezerService implements CreateFreezerUseCase {

    private final FreezerRepositoryPort freezerRepositoryPort;
    private final DomainEventPublisherPort eventPublisher;

    public CreateFreezerService(FreezerRepositoryPort freezerRepositoryPort, DomainEventPublisherPort eventPublisher) {
        this.freezerRepositoryPort = freezerRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Freezer createFreezer(int userId, String freezerName, int shelfQuantity) {
        var freezer = new Freezer(new UserId(userId), freezerName, shelfQuantity);
        freezerRepositoryPort.save(freezer);
        freezer.getDomainEvents().forEach(eventPublisher::publish);
        return freezer;
    }
}
