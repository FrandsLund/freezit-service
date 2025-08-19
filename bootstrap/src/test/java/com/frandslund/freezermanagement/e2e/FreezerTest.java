package com.frandslund.freezermanagement.e2e;

import com.frandslund.freezermanagement.TestProfileWithJpa;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
@TestProfile(TestProfileWithJpa.class)
public class FreezerTest {

    @Test
    void shouldCreateValidFreezer_whenCreateFreezerIsRequested_withValidInput() {
        // Given
        int userId = 123;
        String freezerName = "My new freezer";
        int shelfQuantity = 5;

        String requestBody = "{"
                + "\"userId\": " + userId + ","
                + "\"freezerName\": \"" + freezerName + "\","
                + "\"shelfQuantity\": " + shelfQuantity
                + "}";

        // When
        Response response = given().contentType(ContentType.JSON).body(requestBody).post("/freezers").then().extract().response();

        // Then
        assertThat(response.statusCode()).isEqualTo(RestResponse.StatusCode.CREATED);
        assertThat(response.jsonPath().getString("freezerId")).isNotNull();
        assertThat(response.jsonPath().getInt("userId")).isEqualTo(userId);
        assertThat(response.jsonPath().getString("freezerName")).isEqualTo(freezerName);
        assertThat(response.jsonPath().getList("shelves").size()).isEqualTo(shelfQuantity);
    }
}
