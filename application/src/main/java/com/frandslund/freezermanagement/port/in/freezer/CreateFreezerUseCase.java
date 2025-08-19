package com.frandslund.freezermanagement.port.in.freezer;

import com.frandslund.freezermanagement.model.freezer.Freezer;

public interface CreateFreezerUseCase {

    Freezer createFreezer(int userId, String freezerName, int shelfQuantity);
}

