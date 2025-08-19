package com.frandslund.freezermanagement.model.exception;

import com.frandslund.freezermanagement.model.shelf.ShelfId;

import java.util.NoSuchElementException;

public class ShelfDoesNotExistException extends NoSuchElementException {
    public ShelfDoesNotExistException(int shelfNumber) {
        super("Shelf with id=%d not found".formatted(shelfNumber));
    }
}
