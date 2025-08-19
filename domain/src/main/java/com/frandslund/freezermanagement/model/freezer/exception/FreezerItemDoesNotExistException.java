package com.frandslund.freezermanagement.model.freezer.exception;

import com.frandslund.freezermanagement.model.freezer.FreezerItemId;

import java.util.NoSuchElementException;

public class FreezerItemDoesNotExistException extends NoSuchElementException {
    public FreezerItemDoesNotExistException(FreezerItemId freezerItemId) {
        super("FreezerItem with id=%s not found".formatted(freezerItemId.toString()));
    }
}
