package com.frandslund.freezermanagement.freezer.port.in;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;

// TODO: Remove value objects as input parameter

public interface GetFreezerUseCase {
    Freezer getFreezer(FreezerId freezerId);

    // TODO: Consider creating separate use case for this
    Freezer getFreezer(UserId userId, String freezerName);

}
