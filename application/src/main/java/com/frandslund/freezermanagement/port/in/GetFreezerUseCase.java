package com.frandslund.freezermanagement.port.in;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;

public interface GetFreezerUseCase {
    Freezer getFreezer(FreezerId freezerId);

}
