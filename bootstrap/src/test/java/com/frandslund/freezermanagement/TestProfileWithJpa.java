package com.frandslund.freezermanagement;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class TestProfileWithJpa implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("persistence", "jpa");
    }
}