package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "jpa")
@Transactional
@ApplicationScoped
public class JpaFreezerRepository implements FreezerRepository {

    private final JpaPanacheFreezerRepository panacheRepository;

    private static final Logger LOG = LoggerFactory.getLogger(JpaFreezerRepository.class);

    public JpaFreezerRepository(JpaPanacheFreezerRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }

    @Override
    public Optional<Freezer> findById(FreezerId freezerId) {
        LOG.debug("findById() called: {}", freezerId);
        FreezerEntity freezerEntity = panacheRepository.findById(freezerId.freezerId());
        if (freezerEntity != null) {
            return Optional.of(FreezerMapper.toFreezer(freezerEntity));
        }

        return Optional.empty();
    }

    @Override
    public void save(Freezer freezer) {
        LOG.debug("save() freezer called: {}", freezer);
        panacheRepository.getEntityManager().merge(FreezerMapper.toFreezerEntity(freezer));
    }
}
