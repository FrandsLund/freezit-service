package com.frandslund.freezermanagement.freezer.port.out.persistence;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;

import java.util.Optional;

public interface FreezerRepositoryPort {

    Optional<Freezer> findById(FreezerId freezerId);
    Optional<Freezer> findByUserIdAndFreezerName(UserId userId, String FreezerName);

    void save(Freezer freezer);
}
