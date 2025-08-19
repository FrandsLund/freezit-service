package com.frandslund.freezermanagement.model.freezer.event;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.FreezerItem;

public record FreezerItemQuantityIncreasedEvent(FreezerId freezerId, FreezerItem freezerItem,
                                                int increasedBy) implements DomainEvent {

    @Override
    public String toString() {
        return "FreezerItemQuantityIncreasedEvent{" +
                "freezerId=" + freezerId.freezerId() +
                ", freezerItem=" + freezerItem +
                ", increasedBy=" + increasedBy +
                '}';
    }
}

