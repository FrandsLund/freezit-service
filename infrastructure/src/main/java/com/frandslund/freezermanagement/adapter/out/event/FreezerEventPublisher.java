package com.frandslund.freezermanagement.adapter.out.event;

import com.frandslund.freezermanagement.adapter.in.rest.FreezerResource;
import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.port.out.event.DomainEventPublisher;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Implement class to publish domain events to external queue
@ApplicationScoped
public class FreezerEventPublisher implements DomainEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(FreezerResource.class);

    @Override
    public void publish(DomainEvent event) {
        LOG.info("DomainEvent published: {}", event);
    }
}
