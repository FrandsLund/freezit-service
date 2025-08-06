package com.frandslund.freezermanagement.adapter.in.rest.common;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.core.Response;

public final class ControllerCommons {

    private ControllerCommons() {
    }

    public record ErrorEntity(int httpStatus, String errorMessage) {
    }

    public static ClientErrorException clientErrorException(
            Response.Status status, String message) {
        return new ClientErrorException(errorResponse(status, message));
    }

    public static Response errorResponse(Response.Status status, String message) {
        ErrorEntity errorEntity = new ErrorEntity(status.getStatusCode(), message);
        return Response.status(status).entity(errorEntity).build();
    }
}