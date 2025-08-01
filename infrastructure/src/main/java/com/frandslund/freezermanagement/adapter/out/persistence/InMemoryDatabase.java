package com.frandslund.freezermanagement.adapter.out.persistence;

import com.frandslund.freezermanagement.domain.model.freezer.Freezer;
import com.frandslund.freezermanagement.domain.model.freezer.FreezerId;
import com.frandslund.freezermanagement.application.port.out.persistence.FreezerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
public class InMemoryDatabase implements FreezerRepository {

    @Inject
    Logger log;

    @Override
    public Optional<Freezer> findById(FreezerId freezerId) {
        log.info("findById called");
        return Optional.empty();
    }

    @Override
    public void save(Freezer freezer) {
        log.info("save called");

    }
}
