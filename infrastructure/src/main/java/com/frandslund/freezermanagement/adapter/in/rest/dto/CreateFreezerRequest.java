package com.frandslund.freezermanagement.adapter.in.rest.dto;

public record CreateFreezerRequest(int userId, String freezerName, int shelfQuantity) {
}
