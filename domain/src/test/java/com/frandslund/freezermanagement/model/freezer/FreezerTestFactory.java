package com.frandslund.freezermanagement.model.freezer;

public class FreezerTestFactory {

    public static Freezer createTestFreezer(int userId, String name, int shelfQuantity) {
        return new Freezer(new UserId(userId), name, shelfQuantity);
    }

    public static Freezer createTestFreezerWithThreeShelves() {
        return new Freezer(new UserId(1), "TestFreezer", 3);
    }
}
