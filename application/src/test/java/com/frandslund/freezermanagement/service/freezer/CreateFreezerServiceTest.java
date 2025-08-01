package com.frandslund.freezermanagement.service.freezer;

import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

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
        int quantity = 3;

        // When
        FreezerId id = createFreezerService.createFreezer(name, quantity);

        // Then
        verify(freezerRepository).save(any());
        assertThat(id).isNotNull();
        assertThat(id.freezerId()).isInstanceOf(UUID.class);
    }

}
