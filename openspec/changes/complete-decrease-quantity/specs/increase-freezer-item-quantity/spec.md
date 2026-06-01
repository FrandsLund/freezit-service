## MODIFIED Requirements

### Requirement: Increase freezer item quantity
The system SHALL allow a user to increase the stored quantity of an existing freezer item by a positive integer amount. The endpoint SHALL be `PATCH /freezers/{freezerId}/items/{itemId}/increase` with a request body `{ "by": int }`. The `itemId` SHALL be provided as a path parameter.

#### Scenario: Successful increase
- **WHEN** a `PATCH /freezers/{freezerId}/items/{itemId}/increase` request is made with a valid `by` value
- **THEN** the system increases the item's quantity by the given amount and returns 200 with the updated freezer

#### Scenario: Negative or zero `by` value is rejected
- **WHEN** an increase request is made with `by` less than or equal to zero
- **THEN** the system returns 409 Conflict

#### Scenario: Freezer not found
- **WHEN** an increase request is made with a `freezerId` that does not exist
- **THEN** the system returns 404 Not Found

#### Scenario: Domain event emitted on increase
- **WHEN** a valid increase request is processed
- **THEN** a `FreezerItemQuantityIncreasedEvent` is emitted containing the `freezerId`, the updated `FreezerItem`, and the `increasedBy` delta
