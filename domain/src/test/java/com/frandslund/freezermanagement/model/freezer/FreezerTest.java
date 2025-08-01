package com.frandslund.freezermanagement.model.freezer;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreezerTest {

    @Test
    void firstTest() {
        // Given
        String name = "MyFantasticFreezer";
        int shelfQuantity = 4;

        // When
        var freezer = new Freezer(name, shelfQuantity);

        // Then
        assertThat(freezer.getFreezerId()).isNotNull();
        assertThat(freezer.getAllShelves()).hasSize(shelfQuantity);
        assertThat(freezer.getName()).isEqualTo(name);
    }


}
