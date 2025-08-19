package com.frandslund.freezermanagement.freezer.port.out.messaging;

import com.frandslund.freezermanagement.common.DomainEvent;

public interface DomainEventPublisherPort {
    void publish(DomainEvent event);
}
