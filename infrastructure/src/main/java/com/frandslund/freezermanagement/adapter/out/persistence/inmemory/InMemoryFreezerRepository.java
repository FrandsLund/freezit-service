package com.frandslund.freezermanagement.adapter.out.persistence.inmemory;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class InMemoryFreezerRepository implements FreezerRepository {

    private final Map<FreezerId, Freezer> freezers = new ConcurrentHashMap<>();

    @Override
    public void save(Freezer freezer) {
        freezers.put(freezer.getFreezerId(), freezer);
    }

    @Override
    public Optional<Freezer> findById(FreezerId freezerId) {
        return Optional.ofNullable(freezers.get(freezerId));
    }
}



