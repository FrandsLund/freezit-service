package com.frandslund.freezermanagement.model.freezer;


public record UserId(int userId) {
    public UserId {
        if (userId < 1) {
            throw new IllegalArgumentException("'userId' must be a positive integer, current value: %d".formatted(userId));
        }
    }
}
