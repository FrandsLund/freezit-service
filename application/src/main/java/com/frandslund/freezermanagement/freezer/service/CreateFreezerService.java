package com.frandslund.freezermanagement.freezer.service;

import com.frandslund.freezermanagement.freezer.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.out.messaging.DomainEventPublisherPort;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateFreezerService implements CreateFreezerUseCase {

    private final FreezerRepositoryPort freezerRepositoryPort;
    private final DomainEventPublisherPort eventPublisher;

    public CreateFreezerService(FreezerRepositoryPort freezerRepositoryPort, DomainEventPublisherPort eventPublisher) {
        this.freezerRepositoryPort = freezerRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    private static final Logger LOG = LoggerFactory.getLogger(CreateFreezerService.class);

    // TODO: Look into "Transactional Outbox Pattern" for decoupling emitting events and persisting
    // TODO: @Transactional here is not ideal, as I cannot catch PersistenceException and map it to DomainException,because the try catch block is done before the transaction is committed
    @Override
    @Transactional
    // TODO: Make a test if the event publisher throws an error, that the item was never stored. Probably a e2e test?
    public Freezer createFreezer(int userId, String freezerName, int shelfQuantity) {
        var freezer = new Freezer(new UserId(userId), freezerName, shelfQuantity);
        freezerRepositoryPort.save(freezer);
        freezer
                .getDomainEvents()
                .forEach(eventPublisher::publish);
        return freezer;
    }

//    // TODO: Related to Transactional Outbox Pattern
//    public void onEvent(@Observes @Transactional(Transactional.TxType.AFTER_COMPLETION) AfterCommitEvent event) {
//        if (event.isSuccess()) {
//            publisher.publish(event);
//        } else {
//            throw new DuplicateFreezerNameException("A freezer with this name already exists for the user.");
//        }
//    }
}
