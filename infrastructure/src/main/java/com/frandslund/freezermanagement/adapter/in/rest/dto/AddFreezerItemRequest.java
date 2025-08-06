package com.frandslund.freezermanagement.adapter.in.rest.dto;

public record AddFreezerItemRequest(String itemName, int quantity, String description) {
}
