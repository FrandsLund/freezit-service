## Context

The service uses Hexagonal Architecture across four Maven modules: `domain`, `application`, `infrastructure`, `bootstrap`. The domain already contains `Freezer.decreaseFreezerItemQuantityBy()` and `FreezerItem.decreaseQuantityBy()` with full domain-level tests, but the capability was never wired up through the application and infrastructure layers.

The existing increase-quantity endpoint uses `PATCH /freezers/{freezerId}` with `freezerItemId` in the body — a design that doesn't cleanly extend to a parallel decrease endpoint. Introducing decrease is the right moment to refactor the URL shape.

## Goals / Non-Goals

**Goals:**
- Complete the decrease-quantity vertical slice through all four layers
- Add `FreezerItemQuantityDecreasedEvent` for domain event symmetry
- Refactor the increase endpoint to a resource-centric URL for both operations to share a consistent shape
- Ensure test coverage at domain, service, and E2E level

**Non-Goals:**
- Implementing real event publishing (the stub publisher is intentionally left as-is for now)
- Adding authentication or per-user authorization
- Removing items when quantity reaches zero (zero is a valid state)
- Changing any other endpoints or use cases

## Decisions

### REST URL shape: resource-centric with action suffix

**Decision**: `PATCH /freezers/{freezerId}/items/{itemId}/increase` and `PATCH /freezers/{freezerId}/items/{itemId}/decrease` with body `{ "by": int }`.

**Rationale**: Moving `itemId` into the path follows REST conventions (addressing a specific resource). The action suffix (`/increase`, `/decrease`) makes intent explicit at the URL level, avoiding a signed-quantity trick that the domain explicitly rejects. Both endpoints share an identical body shape.

**Alternative considered**: A single `PATCH /freezers/{freezerId}/items/{itemId}` with a signed quantity (positive = increase, negative = decrease). Rejected because the domain validates that the `by` value must be positive — a negative `by` would be rejected at the domain level, creating a confusing API contract.

### Shared `AdjustQuantityRequest` DTO

**Decision**: One DTO `AdjustQuantityRequest { int by }` shared by both increase and decrease endpoints.

**Rationale**: The request shape is identical — only the operation differs (encoded in the URL). A single DTO avoids duplication. `UpdateFreezerItemQuantityRequest` is renamed and its `freezerItemId` field is dropped since the item ID now comes from the path.

### Domain event for decrease

**Decision**: Add `FreezerItemQuantityDecreasedEvent` mirroring the structure of `FreezerItemQuantityIncreasedEvent`.

**Rationale**: Symmetric events give consumers (future message brokers, audit logs) a complete picture of quantity changes. The `decreasedBy` field records the delta, not the final quantity, consistent with the existing increase event.

## Risks / Trade-offs

- **Breaking API change** → Mitigated by the fact this is a PoC with no external consumers. The change is intentional and documented.
- **`@Transactional` in application services** → Acknowledged technical debt, not addressed in this change. Tracked for a future Thread A change.
- **Events still stubbed** → The `FreezerItemQuantityDecreasedEvent` will be emitted but only logged. Acceptable until Thread E (real event publishing).
