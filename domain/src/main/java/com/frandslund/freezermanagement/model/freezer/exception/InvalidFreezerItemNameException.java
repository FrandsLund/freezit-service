package com.frandslund.freezermanagement.model.freezer.exception;

public class InvalidFreezerItemNameException extends RuntimeException {
    public InvalidFreezerItemNameException(String message) {
        super(message);
    }
}
