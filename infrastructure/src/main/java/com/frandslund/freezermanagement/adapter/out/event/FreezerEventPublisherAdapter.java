package com.frandslund.freezermanagement.adapter.out.event;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.freezer.port.out.messaging.DomainEventPublisherPort;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Implement class to publish domain events to external queue
@ApplicationScoped
public class FreezerEventPublisherAdapter implements DomainEventPublisherPort {

    private static final Logger LOG = LoggerFactory.getLogger(FreezerEventPublisherAdapter.class);

    @Override
    public void publish(DomainEvent event) {
        LOG.info("DomainEvent published: {}", event);
    }
}
