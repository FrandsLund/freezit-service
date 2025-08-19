package com.frandslund.freezermanagement.adapter.in.rest;

import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.freezer.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.freezer.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.in.GetFreezerUseCase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

// TODO: Add more unittests

@QuarkusTest
class FreezerResourceTest {

    @InjectMock
    CreateFreezerUseCase createFreezerUseCase;
    @InjectMock
    GetFreezerUseCase getFreezerUseCase;
    @InjectMock
    AddFreezerItemUseCase addFreezerItemUseCase;

    private final FreezerId FREEZER_ID = new FreezerId(UUID.randomUUID());
    private final UserId USER_ID = new UserId(1);

    @Test
    void givenFreezerDoesNoExist_getFreezer_returnsNotFoundError() {
        // Given
        when(getFreezerUseCase.getFreezer(FREEZER_ID)).thenThrow(NoSuchElementException.class);

        // When
        Response response = given().get("/freezers/" + FREEZER_ID.freezerId().toString()).then().extract().response();

        // Then
        assertThat(response.statusCode()).isEqualTo(RestResponse.StatusCode.NOT_FOUND);
    }

    @Test
    void givenFreezerIsCreated_getFreezer_returnsCreatedFreezer() {
        // Given
        String freezerName = "TestFreezer";
        int shelfQuantity = 3;
        Freezer freezer = new Freezer(USER_ID, freezerName, shelfQuantity);
        when(getFreezerUseCase.getFreezer(FREEZER_ID)).thenReturn(freezer);

        // When
        Response response = given().get("/freezers/" + FREEZER_ID.freezerId().toString()).then().extract().response();

        // Then
        assertThat(response.statusCode()).isEqualTo(RestResponse.StatusCode.OK);
    }

}