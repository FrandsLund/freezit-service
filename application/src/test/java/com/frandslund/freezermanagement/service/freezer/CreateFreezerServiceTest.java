package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.port.out.event.freezer.DomainEventPublisher;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// TODO: Update test naming
public class CreateFreezerServiceTest {

    private final FreezerRepository freezerRepository = mock(FreezerRepository.class);
    private final DomainEventPublisher domainEventPublisher = mock(DomainEventPublisher.class);
    private final CreateFreezerService createFreezerService = new CreateFreezerService(freezerRepository, domainEventPublisher);

    @Test
    void createFreezer_freezerCreated() {
        // Given
        int userId = 5;
        String name = "TestFreezer";
        int shelfQuantity = 3;

        // When
        Freezer freezer = createFreezerService.createFreezer(userId, name, shelfQuantity);

        // Then
        verify(freezerRepository).save(any(Freezer.class));
        verify(domainEventPublisher).publish(any(DomainEvent.class));
        assertThat(freezer).isNotNull();
        assertThat(freezer.getFreezerId().freezerId()).isInstanceOf(UUID.class);
        assertThat(freezer.getName()).isEqualTo(name);
        assertThat(freezer.getShelves()).hasSize(shelfQuantity);
        assertThat(freezer.getUserId().userId()).isEqualTo(userId);
    }
}
