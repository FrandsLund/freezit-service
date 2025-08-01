package com.frandslund.freezermanagement.model.freezeritem;

import java.time.Instant;


// TODO: Add validation
public record ItemData(String name, int quantity, String description, Instant dateAdded) {


}
