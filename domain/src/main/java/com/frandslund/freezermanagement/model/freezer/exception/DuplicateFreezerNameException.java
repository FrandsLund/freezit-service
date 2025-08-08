package com.frandslund.freezermanagement.model.freezer.exception;

public class DuplicateFreezerNameException extends RuntimeException {
    public DuplicateFreezerNameException(String message) {
        super(message);
    }

}
