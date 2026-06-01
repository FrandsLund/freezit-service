## 1. Domain Layer

- [ ] 1.1 Create `FreezerItemQuantityDecreasedEvent` record in `domain/src/main/java/.../model/freezer/event/` mirroring `FreezerItemQuantityIncreasedEvent` (fields: `freezerId`, `freezerItem`, `decreasedBy`)
- [ ] 1.2 Emit `FreezerItemQuantityDecreasedEvent` from `Freezer.decreaseFreezerItemQuantityBy()` (mirrors how `increaseFreezerItemQuantityBy` emits its event)
- [ ] 1.3 Add domain test: verify `FreezerItemQuantityDecreasedEvent` is emitted with correct fields when `decreaseFreezerItemQuantityBy()` is called

## 2. Application Layer

- [ ] 2.1 Create `DecreaseFreezerItemQuantityUseCase` interface in `application/src/main/java/.../freezer/port/in/` (mirrors `IncreaseFreezerItemQuantityUseCase`)
- [ ] 2.2 Create `DecreaseFreezerItemQuantityService` in `application/src/main/java/.../freezer/service/` (mirrors `IncreaseFreezerItemQuantityService`, calls `freezer.decreaseFreezerItemQuantityBy()`)
- [ ] 2.3 Add service test for `DecreaseFreezerItemQuantityService`: verify `save()` is called, event is published, and updated freezer is returned

## 3. Infrastructure — Refactor Increase Endpoint

- [ ] 3.1 Rename `UpdateFreezerItemQuantityRequest` to `AdjustQuantityRequest` with a single field `int by` (drop `freezerItemId` — item ID moves to path)
- [ ] 3.2 Refactor `PATCH /freezers/{freezerId}` in `FreezerResource` to `PATCH /freezers/{freezerId}/items/{itemId}/increase`, updating path params and DTO usage

## 4. Infrastructure — Add Decrease Endpoint

- [ ] 4.1 Add `PATCH /freezers/{freezerId}/items/{itemId}/decrease` endpoint to `FreezerResource` using `DecreaseFreezerItemQuantityUseCase` and `AdjustQuantityRequest`
- [ ] 4.2 Map `InvalidFreezerItemQuantityException` to 409 Conflict in the decrease handler (consistent with how increase handles it)
- [ ] 4.3 Wire `DecreaseFreezerItemQuantityUseCase` in `QuarkusAppConfig` using `@Produces`

## 5. Tests

- [ ] 5.1 Update existing `FreezerResourceTest` or infrastructure test to reflect the refactored increase endpoint path
- [ ] 5.2 Add E2E test in `bootstrap/` for the full decrease flow: create freezer → add item → decrease quantity → assert updated quantity
- [ ] 5.3 Add E2E scenario: decrease below zero returns 409 and quantity is unchanged
