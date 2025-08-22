package com.frandslund.freezermanagement.model.freezer.exception;

public class InvalidFreezerItemQuantityException extends RuntimeException {

    public InvalidFreezerItemQuantityException(String message) {
        super(message);
    }
}