package com.frandslund.freezermanagement.model.freezer.exception;

import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.util.NoSuchElementException;

public class FreezerNotFoundException extends NoSuchElementException {
    public FreezerNotFoundException(FreezerId freezerId) {
        super("Freezer with id=%s not found".formatted(freezerId.toString()));
    }

    public FreezerNotFoundException(String freezerName) {
        super("Freezer with name=%s not found".formatted(freezerName));
    }
}
