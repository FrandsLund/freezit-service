package com.frandslund.freezermanagement.model.freezer;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class FreezerTest {

    @Test
    void createNewFreezer_freezerInitializedCorrect() {
        // Given
        int userId = 1;
        String name = "MyFantasticFreezer";
        int shelfQuantity = 4;

        // When
        var freezer = new Freezer(new UserId(userId), name, shelfQuantity);

        // Then
        assertThat(freezer.getFreezerId()).isNotNull();
        assertThat(freezer.getShelves()).hasSize(4);
        assertThat(freezer.getName()).isEqualTo(name);
    }

    // TODO: Consider making this test better asserting on the return value
    @Test
    void addFreezerItem_validFreezerItemReturned() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = 11;
        String name = "Chicken";
        String description = "";
        Instant dateAdded = Instant.ofEpochSecond(1L);

        // When
        freezer.addFreezerItem(shelfNumber, quantity, name, description, dateAdded);

        // Then
        var alFreezerItems = freezer.getShelves().stream().flatMap(shelf -> shelf.getFreezerItems().stream()).toList();
        assertThat(alFreezerItems).hasSize(1);
    }

    // more tests
}
