package com.frandslund.freezermanagement.freezer.port.in;

import com.frandslund.freezermanagement.model.freezer.Freezer;

public interface IncreaseFreezerItemQuantityUseCase {
    Freezer increaseFreezerItemQuantity(String freezerId, String freezerItemId, int increaseBy);
}
