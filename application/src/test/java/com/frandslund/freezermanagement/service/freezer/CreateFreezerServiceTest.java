package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateFreezerServiceTest {

    private final FreezerRepository freezerRepository = mock(FreezerRepository.class);
    private final CreateFreezerService createFreezerService = new CreateFreezerService(freezerRepository);

    @Test
    void createFreezer_freezerCreated() {
        // Given
        String name = "TestFreezer";
        int shelfQuantity = 3;

        // When
        Freezer freezer = createFreezerService.createFreezer(name, shelfQuantity);

        // Then
        verify(freezerRepository).save(any());
        assertThat(freezer).isNotNull();
        assertThat(freezer.getFreezerId().freezerId()).isInstanceOf(UUID.class);
        assertThat(freezer.getName()).isEqualTo(name);
        assertThat(freezer.getShelves()).hasSize(shelfQuantity);
    }

}
