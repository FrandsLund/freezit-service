package com.frandslund.freezermanagement.port.in;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.time.Instant;

public interface FreezerService {

    FreezerId createFreezer(String name);

    void addFreezerItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded);

    Freezer getFreezer(FreezerId freezerId);
}

