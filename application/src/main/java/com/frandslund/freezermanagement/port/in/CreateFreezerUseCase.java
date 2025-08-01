package com.frandslund.freezermanagement.port.in;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.time.Instant;

public interface CreateFreezerUseCase {

    FreezerId createFreezer(String name, int shelfQuantity);
}

