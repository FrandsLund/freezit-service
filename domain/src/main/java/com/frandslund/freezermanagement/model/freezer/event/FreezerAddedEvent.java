package com.frandslund.freezermanagement.model.freezer.event;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.model.freezer.FreezerId;

public record FreezerAddedEvent(FreezerId freezerId) implements DomainEvent {
}
