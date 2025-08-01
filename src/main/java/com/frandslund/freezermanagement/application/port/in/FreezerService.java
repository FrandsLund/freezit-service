package com.frandslund.freezermanagement.application.port.in;

import com.frandslund.freezermanagement.domain.model.Freezer;
import com.frandslund.freezermanagement.domain.model.FreezerId;

import java.time.Instant;

public interface FreezerService {

    FreezerId createFreezer(String name);

    void addShelf(FreezerId freezerId, int shelfNumber);

    void addFreezerItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded);

    Freezer getFreezer(FreezerId freezerId);
}

