package com.frandslund.freezermanagement.port.out.persistence;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.util.Optional;

public interface FreezerRepository {

    Optional<Freezer> findById(FreezerId freezerId);

    void save(Freezer freezer);
}
