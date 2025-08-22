package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.model.freezer.exception.DuplicateFreezerNameException;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

// TODO: Find ud af, hvorfor denne ikke bare implementerer PanacheRepositoryBase

@LookupIfProperty(name = "persistence", stringValue = "jpa")
@ApplicationScoped
public class JpaFreezerRepositoryAdapter implements FreezerRepositoryPort {

    private final JpaPanacheFreezerRepository panacheRepository;
    private static final Logger LOG = LoggerFactory.getLogger(JpaFreezerRepositoryAdapter.class);
    // TODO: Constructor inject, create test with mock, where userTransaction throws ConstraintViolation exception
    @Inject
    private UserTransaction userTransaction;

    public JpaFreezerRepositoryAdapter(JpaPanacheFreezerRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
        LOG.info("Freezer repository initialized");
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
    public Optional<Freezer> findByUserIdAndFreezerName(UserId userId, String freezerName) {
        return panacheRepository
                .find("userId = ?1 and name = ?2", userId.userId(), freezerName)
                .firstResultOptional()
                .map(FreezerMapper::toFreezer);
    }

    // TODO: Might not be the ideal way of doing this...
    @Override
    public void save(Freezer freezer) {
        LOG.debug("Save freezer called, freezer={}", freezer);
        try {
            userTransaction.begin();
            panacheRepository
                    .getEntityManager()
                    .merge(FreezerMapper.toFreezerEntity(freezer));
            userTransaction.commit();
        } catch (RollbackException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateFreezerNameException("A freezer with this name already exists for the user");
            }
            throw new RuntimeException(e);
        } catch (HeuristicRollbackException | SystemException | HeuristicMixedException | NotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
