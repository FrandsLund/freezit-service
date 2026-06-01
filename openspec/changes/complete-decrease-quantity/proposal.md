## Why

The domain already supports decreasing freezer item quantity, but the capability is orphaned — no use case, no service, and no REST endpoint exist. This leaves a complete vertical slice half-built and the domain event never emitted on decrease. Completing it walks the full Hexagonal stack and brings the domain event model to symmetry.

## What Changes

- Add `FreezerItemQuantityDecreasedEvent` domain event (mirrors the existing increase event)
- Emit the new domain event from `Freezer.decreaseFreezerItemQuantityBy()`
- Add `DecreaseFreezerItemQuantityUseCase` inbound port
- Add `DecreaseFreezerItemQuantityService` application service
- **BREAKING**: Refactor existing `PATCH /freezers/{freezerId}` increase endpoint to `PATCH /freezers/{freezerId}/items/{itemId}/increase`
- Add `PATCH /freezers/{freezerId}/items/{itemId}/decrease` endpoint
- Rename `UpdateFreezerItemQuantityRequest` DTO to `AdjustQuantityRequest` with body `{ "by": int }`
- Wire new service in `QuarkusAppConfig`
- Add tests at every layer: domain event, service (mocked), and E2E

## Capabilities

### New Capabilities

- `decrease-freezer-item-quantity`: Decrease the stored quantity of an existing freezer item by a given amount. Quantity may reach zero (item remains on shelf). Emits a `FreezerItemQuantityDecreasedEvent`.

### Modified Capabilities

- `increase-freezer-item-quantity`: Endpoint path changes from `PATCH /freezers/{freezerId}` to `PATCH /freezers/{freezerId}/items/{itemId}/increase` for consistency with the new decrease endpoint. Request body changes from `{ freezerItemId, quantity }` to `{ "by": int }`.

## Impact

- **domain/**: New event class, one-line addition to `Freezer.java`
- **application/**: Two new files (port + service), one new test class
- **infrastructure/**: Refactored REST endpoint, new REST endpoint, renamed DTO, updated `QuarkusAppConfig`
- **bootstrap/**: One new E2E test
- **Breaking API change**: Existing `PATCH /freezers/{freezerId}` increase endpoint moves. Acceptable for a PoC with no external consumers.
