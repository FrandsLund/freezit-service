package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.port.out.event.freezer.DomainEventPublisherPort;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepositoryPort;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateFreezerServiceTest {

    private final FreezerRepositoryPort freezerRepositoryPort = mock(FreezerRepositoryPort.class);
    private final DomainEventPublisherPort domainEventPublisherPort = mock(DomainEventPublisherPort.class);
    private final CreateFreezerService createFreezerService = new CreateFreezerService(freezerRepositoryPort, domainEventPublisherPort);

    @Test
    void shouldCreateFreezer_whenCreateFreezerIsRequestedWithValidInput() {
        // Given
        int userId = 5;
        String name = "TestFreezer";
        int shelfQuantity = 3;

        // When
        Freezer freezer = createFreezerService.createFreezer(userId, name, shelfQuantity);

        // Then
        verify(freezerRepositoryPort).save(any(Freezer.class));
        verify(domainEventPublisherPort).publish(any(DomainEvent.class));
        assertThat(freezer).isNotNull();
        assertThat(freezer.getFreezerId().freezerId()).isInstanceOf(UUID.class);
        assertThat(freezer.getName()).isEqualTo(name);
        assertThat(freezer.getShelves()).hasSize(shelfQuantity);
        assertThat(freezer.getUserId().userId()).isEqualTo(userId);
    }
}
