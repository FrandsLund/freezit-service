package com.frandslund.freezermanagement.adapter.out.persistence;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractFreezerRepositoryTest {

    @Inject
    Instance<FreezerRepositoryPort> freezerRepositoryInstance;

    private FreezerRepositoryPort freezerRepository;

    @BeforeEach
    void initRepository() {
        freezerRepository = freezerRepositoryInstance.get();
    }

    @Test
    void freezerCreated_correctValuesSavedInRepo() {
        // Given
        Freezer freezer = new Freezer(new UserId(3), "MyFreezer", 3);

        // When
        freezerRepository.save(freezer);
        var fetchedFreezer = freezerRepository.findById(freezer.getFreezerId());

        // Then
        assertThat(fetchedFreezer).hasValueSatisfying(f -> {
            assertThat(f.getFreezerId()).isEqualTo(freezer.getFreezerId());
            assertThat(f.getUserId()).isEqualTo(freezer.getUserId());
            assertThat(f.getName()).isEqualTo(freezer.getName());
            assertThat(f.getShelves()).hasSize(freezer.getShelves().size());
        });
    }
}
