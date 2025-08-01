package com.frandslund.freezermanagement.application.port.out.persistence;

import com.frandslund.freezermanagement.domain.model.Freezer;
import com.frandslund.freezermanagement.domain.model.FreezerId;

import java.util.Optional;

public interface FreezerRepository {
    Optional<Freezer> findById(FreezerId freezerId);

    void save(Freezer freezer);
}
