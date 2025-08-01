package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.GetFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.security.InvalidParameterException;

@ApplicationScoped
public class GetFreezerService implements GetFreezerUseCase {

    private final FreezerRepository freezerRepository;

    public GetFreezerService(FreezerRepository freezerRepository) {
        this.freezerRepository = freezerRepository;
    }

    @Override
    public Freezer getFreezer(FreezerId freezerId) {
        return freezerRepository.findById(freezerId).orElseThrow(() -> new InvalidParameterException("Freezer with ID " + freezerId.freezerId().toString() + " not found."));
    }
}
