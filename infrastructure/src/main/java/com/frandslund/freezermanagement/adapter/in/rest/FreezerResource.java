package com.frandslund.freezermanagement.adapter.in.rest;


import com.frandslund.freezermanagement.adapter.in.rest.dto.AddFreezerItemRequest;
import com.frandslund.freezermanagement.adapter.in.rest.dto.CreateFreezerRequest;
import com.frandslund.freezermanagement.adapter.in.rest.dto.FreezerWebModel;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.in.GetFreezerUseCase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.frandslund.freezermanagement.adapter.in.rest.common.ControllerCommons.clientErrorException;

/**
 * @apiNote Primary REST adapter for interacting with the application. Could be one resource per use case.
 */
@Path("/freezers")
@Produces(MediaType.APPLICATION_JSON)
public class FreezerResource {
    private final CreateFreezerUseCase createFreezerUseCase;
    private final GetFreezerUseCase getFreezerUseCase;
    private final AddFreezerItemUseCase addFreezerItemUseCase;

    private static final Logger LOG = LoggerFactory.getLogger(FreezerResource.class);


    public FreezerResource(CreateFreezerUseCase createFreezerUseCase, GetFreezerUseCase getFreezerUseCase, AddFreezerItemUseCase addFreezerItemUseCase) {
        this.createFreezerUseCase = createFreezerUseCase;
        this.getFreezerUseCase = getFreezerUseCase;
        this.addFreezerItemUseCase = addFreezerItemUseCase;
    }

    @GET
    @Path("/{freezerId}")
    @APIResponse(
            responseCode = "200",
            description = "Get freezer by freezerId",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FreezerWebModel.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Freezer does not exist for freezerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public FreezerWebModel getFreezer(@PathParam("freezerId") String freezerId) {
        try {
            Freezer freezer = getFreezerUseCase.getFreezer(new FreezerId(UUID.fromString(freezerId)));
            return FreezerWebModel.fromDomainModel(freezer);
        } catch (NoSuchElementException e) {
            throw clientErrorException(
                    Response.Status.NOT_FOUND, "The requested freezer does not exist");
        }
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Freezer created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FreezerWebModel.class)
            )
    )
    public Response createFreezer(CreateFreezerRequest createFreezerRequest) {
        LOG.info("Create freezer requested");
        Freezer freezer = createFreezerUseCase.createFreezer(createFreezerRequest.userId(), createFreezerRequest.freezerName(), createFreezerRequest.shelfQuantity());
        FreezerWebModel freezerWebModel = FreezerWebModel.fromDomainModel(freezer);
        return Response.created(URI.create("/freezers/" + freezer.getFreezerId().freezerId()))
                .entity(freezerWebModel)
                .build();
    }

    @POST
    @Path("/{freezerId}/{shelfNumber}")
    @APIResponse(
            responseCode = "201",
            description = "Freezer item created",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FreezerWebModel.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Freezer does not exist for freezerId",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response addFreezerItem(@PathParam("freezerId") String freezerId, @PathParam("shelfNumber") int shelfNumber, AddFreezerItemRequest addFreezerItemRequest) {
        try {
            Freezer freezer = addFreezerItemUseCase.addFreezerItemUseCase(new FreezerId(freezerId), shelfNumber, addFreezerItemRequest.itemName(), addFreezerItemRequest.quantity(), addFreezerItemRequest.description());
            FreezerWebModel freezerWebModel = FreezerWebModel.fromDomainModel(freezer);
            return Response.created(URI.create("/freezers/" + freezer.getFreezerId().freezerId()))
                    .entity(freezerWebModel)
                    .build();
        } catch (NoSuchElementException e) {
            throw clientErrorException(
                    Response.Status.NOT_FOUND, "The requested freezer does not exist");
        }
    }
}

