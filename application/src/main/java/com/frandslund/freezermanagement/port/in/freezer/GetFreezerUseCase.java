package com.frandslund.freezermanagement.port.in.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;

public interface GetFreezerUseCase {
    Freezer getFreezer(FreezerId freezerId);

    // TODO: Consider creating separate use case for this
    Freezer getFreezer(UserId userId, String freezerName);

}
