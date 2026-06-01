## ADDED Requirements

### Requirement: Decrease freezer item quantity
The system SHALL allow a user to decrease the stored quantity of an existing freezer item by a positive integer amount. The item SHALL remain on the shelf when its quantity reaches zero.

#### Scenario: Successful decrease
- **WHEN** a `PATCH /freezers/{freezerId}/items/{itemId}/decrease` request is made with a valid `by` value
- **THEN** the system decreases the item's quantity by the given amount and returns 200 with the updated freezer

#### Scenario: Decrease to zero
- **WHEN** a decrease request is made where `by` equals the item's current quantity
- **THEN** the system sets the item's quantity to zero and returns 200 — the item is NOT removed

#### Scenario: Decrease below zero is rejected
- **WHEN** a decrease request is made where `by` exceeds the item's current quantity
- **THEN** the system returns 409 Conflict and the item's quantity is unchanged

#### Scenario: Negative or zero `by` value is rejected
- **WHEN** a decrease request is made with `by` less than or equal to zero
- **THEN** the system returns 409 Conflict

#### Scenario: Freezer not found
- **WHEN** a decrease request is made with a `freezerId` that does not exist
- **THEN** the system returns 404 Not Found

#### Scenario: Domain event emitted on decrease
- **WHEN** a valid decrease request is processed
- **THEN** a `FreezerItemQuantityDecreasedEvent` is emitted containing the `freezerId`, the updated `FreezerItem`, and the `decreasedBy` delta
