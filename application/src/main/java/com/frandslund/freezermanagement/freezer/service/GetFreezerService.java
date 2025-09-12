package com.frandslund.freezermanagement.freezer.service;

import com.frandslund.freezermanagement.freezer.port.in.GetFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.model.freezer.exception.FreezerNotFoundException;

public class GetFreezerService implements GetFreezerUseCase {

    private final FreezerRepositoryPort freezerRepositoryPort;

    public GetFreezerService(FreezerRepositoryPort freezerRepositoryPort) {
        this.freezerRepositoryPort = freezerRepositoryPort;
    }

    @Override
    public Freezer getFreezer(FreezerId freezerId) {
        return freezerRepositoryPort
                .findById(freezerId)
                .orElseThrow(() -> new FreezerNotFoundException(freezerId));
    }

    @Override
    public Freezer getFreezer(UserId userId, String freezerName) {
        return freezerRepositoryPort
                .findByUserIdAndFreezerName(userId, freezerName)
                .orElseThrow(() -> new FreezerNotFoundException(freezerName));
    }
}
