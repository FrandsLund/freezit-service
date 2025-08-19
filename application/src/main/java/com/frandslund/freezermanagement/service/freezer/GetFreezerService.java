package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.port.in.freezer.GetFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepositoryPort;

import java.util.NoSuchElementException;

public class GetFreezerService implements GetFreezerUseCase {

    private final FreezerRepositoryPort freezerRepositoryPort;

    public GetFreezerService(FreezerRepositoryPort freezerRepositoryPort) {
        this.freezerRepositoryPort = freezerRepositoryPort;
    }

    @Override
    public Freezer getFreezer(FreezerId freezerId) throws NoSuchElementException {
        return freezerRepositoryPort
                .findById(freezerId).orElseThrow(() -> new NoSuchElementException("Freezer with ID " + freezerId.freezerId().toString() + " not found."));
    }

    @Override
    public Freezer getFreezer(UserId userId, String freezerName) {
        return freezerRepositoryPort
                .findByUserIdAndFreezerName(userId, freezerName).orElseThrow(() -> new NoSuchElementException("Freezer not found"));
    }
}
