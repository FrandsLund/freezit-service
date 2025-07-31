package com.frandslund.freezermanagement.domain.model;

import com.frandslund.freezermanagement.domain.common.ValueObject;

import java.time.Instant;


// TODO: Add validation
public record ItemData(String name, int quantity, String description, Instant dateAdded) {


}
