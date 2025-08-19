package com.frandslund.freezermanagement.model;

import com.frandslund.freezermanagement.common.DomainEvent;
import com.frandslund.freezermanagement.event.FreezerAddedEvent;
import com.frandslund.freezermanagement.event.FreezerItemAddedEvent;
import com.frandslund.freezermanagement.model.exception.ShelfDoesNotExistException;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.model.freezeritem.FreezerItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FreezerTest {

    @Test
    void shouldCreateFreezer_whenValidInputIsPassed() {
        // Given
        int userId = 1;
        String name = "MyFantasticFreezer";
        int shelfQuantity = 4;

        // When
        var freezer = new Freezer(new UserId(userId), name, shelfQuantity);

        // Then
        assertThat(freezer.getFreezerId()).isNotNull();
        assertThat(freezer.getShelves()).hasSize(shelfQuantity);
        assertThat(freezer.getName()).isEqualTo(name);
        assertThat(freezer
                           .getUserId()
                           .userId()).isEqualTo(userId);
    }

    @Test
    void shouldAddDomainEvent_whenValidFreezerIsCreated() {
        // When
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        // Then
        List<DomainEvent> domainEvents = freezer.getDomainEvents();
        assertThat(domainEvents).hasSize(1);
        assertThat(domainEvents.get(0)).isInstanceOf(FreezerAddedEvent.class);
        assertThat(((FreezerAddedEvent) domainEvents.get(0)).freezerId()).isEqualTo(freezer.getFreezerId());
    }

    @ParameterizedTest
    @CsvSource({"-1", "0"})
    void shouldThrowException_whenFreezerIsCreated_givenInvalidShelfQuantity(int shelfQuantity) {
        // When / then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Freezer(new UserId(1), "MyFantasticFreezer", shelfQuantity);
                })
                .withMessage("'shelfQuantity' must be greater than 0, current value: %s".formatted(shelfQuantity));
    }

    @Test
    void shouldAddFreezerItemToFreezer_whenValidItemIsAdded() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = 11;
        String name = "Chicken";
        String description = "Pre cooked";

        // When
        freezer.addFreezerItem(shelfNumber, quantity, name, description);

        // Then
        List<FreezerItem> freezerItems = getFreezerItems(freezer);
        assertThat(freezerItems).hasSize(1);
        FreezerItem freezerItem = freezerItems.get(0);

        assertThat(freezerItem.getFreezerItemId()).isNotNull();
        assertThat(freezerItem.getQuantity()).isEqualTo(quantity);
        assertThat(freezerItem
                           .getItemData()
                           .name()).isEqualTo(name);
        assertThat(freezerItem
                           .getItemData()
                           .description()).isEqualTo(description);
        assertThat(freezerItem
                           .getItemData()
                           .dateAdded()).isNotNull();
    }


    @Test
    void shouldThrowException_whenFreezerItemIsAdded_givenNegativeQuantity() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = -1;
        String name = "Chicken";
        String description = "Pre cooked";

        // When / then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    freezer.addFreezerItem(shelfNumber, quantity, name, description);
                })
                .withMessage("'quantity' must not be negative, current value: %s".formatted(quantity));
    }

    @Test
    void shouldThrowException_whenFreezerItemIsAdded_givenEmptyName() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = 0;
        String name = "";
        String description = "Pre cooked";

        // When / then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    freezer.addFreezerItem(shelfNumber, quantity, name, description);
                })
                .withMessage("'name' cannot be empty");
    }

    @Test
    void shouldAddDomainEvent_whenValidFreezerItemIsAdded() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 2;
        int quantity = 11;
        String name = "Chicken";
        String description = "Pre cooked";

        // When
        freezer.addFreezerItem(shelfNumber, quantity, name, description);

        // Then
        List<FreezerItem> freezerItems = getFreezerItems(freezer);
        List<DomainEvent> domainEvents = freezer.getDomainEvents();
        assertThat(domainEvents).hasSize(2);
        assertThat(domainEvents.get(1)).isInstanceOf(FreezerItemAddedEvent.class);
        assertThat(((FreezerItemAddedEvent) domainEvents.get(1)).freezerItemId()).isEqualTo(freezerItems
                                                                                                    .get(0)
                                                                                                    .getFreezerItemId());
    }

    @Test
    void shouldThrowException_whenFreezerItemIsAdded_givenNonExistingShelfNumber() {
        // Given
        var freezer = FreezerTestFactory.createTestFreezerWithThreeShelves();

        int shelfNumber = 4;
        int quantity = 0;
        String name = "Chicken";
        String description = "Pre cooked";

        // When / then
        assertThatExceptionOfType(ShelfDoesNotExistException.class)
                .isThrownBy(() -> {
                    freezer.addFreezerItem(shelfNumber, quantity, name, description);
                })
                .withMessage("Shelf with id=%d not found".formatted(shelfNumber));
    }

    private List<FreezerItem> getFreezerItems(Freezer freezer) {
        return freezer
                .getShelves()
                .stream()
                .flatMap(shelf -> shelf
                        .getFreezerItems()
                        .stream())
                .toList();
    }
}
