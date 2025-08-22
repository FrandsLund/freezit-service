package com.frandslund.freezermanagement.model.freezer.exception;

import com.frandslund.freezermanagement.model.freezer.FreezerItemId;

import java.util.NoSuchElementException;

public class FreezerItemNotFoundException extends NoSuchElementException {
    public FreezerItemNotFoundException(FreezerItemId freezerItemId) {
        super("FreezerItem with id=%s not found".formatted(freezerItemId.freezerItemId().toString()));
    }
}
