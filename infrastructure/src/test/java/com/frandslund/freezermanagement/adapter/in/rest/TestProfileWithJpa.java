package com.frandslund.freezermanagement.adapter.in.rest;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

// TODO: Consider removing

public class TestProfileWithJpa implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("persistence", "jpa");
    }
}