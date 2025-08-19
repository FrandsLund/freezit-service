package com.frandslund.freezermanagement.model;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.UserId;

public class FreezerTestFactory {

    public static Freezer createTestFreezer(int userId, String name, int shelfQuantity) {
        return new Freezer(new UserId(userId), name, shelfQuantity);
    }

    public static Freezer createTestFreezerWithThreeShelves() {
        return new Freezer(new UserId(1), "TestFreezer", 3);
    }

    public static Freezer createTestFreezerWithOneShelfAndOneFreezerItem(int freezerItemQuantity) {
        int shelfQuantity = 1;
        var freezer = new Freezer(new UserId(1), "TestFreezer", 1);
        freezer.addFreezerItem(shelfQuantity, freezerItemQuantity, "Chicken", "Pre cooked");
        return freezer;
    }

    public static Freezer createTestFreezerWithOneShelfAndOneFreezerItem() {
        return createTestFreezerWithOneShelfAndOneFreezerItem(1);
    }
}
