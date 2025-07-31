package com.frandslund.freezermanagement.domain.port.in;

import com.frandslund.freezermanagement.domain.model.Freezer;
import com.frandslund.freezermanagement.domain.model.FreezerId;
import com.frandslund.freezermanagement.domain.model.FreezerItem;
import com.frandslund.freezermanagement.domain.port.out.FreezerRepository;

import java.time.Instant;
import java.util.UUID;

public interface FreezerService {

    FreezerId createFreezer(String name);

    void addShelf(FreezerId freezerId, int shelfNumber);

    void addFreezerItem(FreezerId freezerId, int shelfNumber, String name, int quantity, String description, Instant dateAdded);

    Freezer getFreezer(FreezerId freezerId);
}

