package com.frandslund.freezermanagement.model.freezer;

public class FreezerTestFactory {

    public static Freezer createTestFreezer(String name, int shelfQuantity) {
        return new Freezer(name, shelfQuantity);
    }

    public static Freezer createTestFreezerWithThreeShelves() {
        return new Freezer("TestFreezer", 3);
    }
}
