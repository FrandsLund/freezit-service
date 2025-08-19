package com.frandslund.freezermanagement.port.out.persistence.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;

import java.util.Optional;

public interface FreezerRepository {

    Optional<Freezer> findById(FreezerId freezerId);
    Optional<Freezer> findByUserIdAndFreezerName(UserId userId, String FreezerName);

    void save(Freezer freezer);
}
