package com.frandslund.freezermanagement.model.freezer;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class FreezerTest {

    @Test
    void createNewFreezer_freezerInitializedCorrect() {
        // Given
        String name = "MyFantasticFreezer";
        int shelfQuantity = 4;

        // When
        var freezer = new Freezer(name, shelfQuantity);

        // Then
        assertThat(freezer.getFreezerId()).isNotNull();
        assertThat(freezer.getAllShelves()).hasSize(4);
        assertThat(freezer.getName()).isEqualTo(name);
    }

    // TODO: Consider making this test better asserting on the return value
    @Test
    void addInventoryItem_validItemReturned() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = 11;
        String name = "Chicken";
        String description = "";
        Instant dateAdded = Instant.ofEpochSecond(1L);

        // When
        freezer.addInventoryItem(shelfNumber, quantity, name, description, dateAdded);

        // Then
        var allInventoryItems = freezer.getAllShelves().stream().flatMap(shelf -> shelf.getInventoryItems().stream()).toList();
        assertThat(allInventoryItems).hasSize(1);
    }

    // more tests
}
