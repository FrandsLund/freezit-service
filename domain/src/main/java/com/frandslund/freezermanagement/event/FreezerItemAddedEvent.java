package com.frandslund.freezermanagement.event;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.FreezerItemId;

public record FreezerItemAddedEvent(FreezerId freezerId, FreezerItemId freezerItemId) implements DomainEvent {

}
