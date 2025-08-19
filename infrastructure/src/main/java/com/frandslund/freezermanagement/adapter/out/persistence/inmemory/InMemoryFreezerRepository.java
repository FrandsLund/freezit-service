package com.frandslund.freezermanagement.adapter.out.persistence.inmemory;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepository;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class InMemoryFreezerRepository implements FreezerRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryFreezerRepository.class);
    private final Map<FreezerId, Freezer> freezers = new ConcurrentHashMap<>();

    public InMemoryFreezerRepository() {
        LOG.info("Freezer repository initialized");
    }

    @Override
    public void save(Freezer freezer) {
        freezers.put(freezer.getFreezerId(), freezer);
    }

    @Override
    public Optional<Freezer> findById(FreezerId freezerId) {
        return Optional.ofNullable(freezers.get(freezerId));
    }

    @Override
    public Optional<Freezer> findByUserIdAndFreezerName(UserId userId, String FreezerName) {
        LOG.error("Unimplemented method: findUserIdAndFreezerName()");
        return Optional.empty();
    }
}