package com.frandslund.freezermanagement.port.out.event;

import com.frandslund.freezermanagement.common.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
