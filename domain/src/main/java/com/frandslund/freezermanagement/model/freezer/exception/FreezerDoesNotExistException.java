package com.frandslund.freezermanagement.model.freezer.exception;

import com.frandslund.freezermanagement.model.freezer.FreezerId;

import java.util.NoSuchElementException;

public class FreezerDoesNotExistException extends NoSuchElementException {
    public FreezerDoesNotExistException(FreezerId freezerId) {
        super("Freezer with id=%s not found".formatted(freezerId.toString()));
    }

    public FreezerDoesNotExistException(String freezerName) {
        super("Freezer with name=%s not found".formatted(freezerName));
    }
}
