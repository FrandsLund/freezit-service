package com.frandslund.freezermanagement.adapter.in.rest;


import com.frandslund.freezermanagement.adapter.in.rest.dto.AddFreezerItemRequest;
import com.frandslund.freezermanagement.adapter.in.rest.dto.CreateFreezerRequest;
import com.frandslund.freezermanagement.adapter.in.rest.dto.FreezerWebModel;
import com.frandslund.freezermanagement.adapter.in.rest.dto.UpdateFreezerItemQuantityRequest;
import com.frandslund.freezermanagement.freezer.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.freezer.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.in.GetFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.in.IncreaseFreezerItemQuantityUseCase;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.model.freezer.FreezerItemId;
import com.frandslund.freezermanagement.model.freezer.UserId;
import com.frandslund.freezermanagement.model.freezer.exception.DuplicateFreezerNameException;
import com.frandslund.freezermanagement.model.freezer.exception.FreezerNotFoundException;
import com.frandslund.freezermanagement.model.freezer.exception.InvalidFreezerItemQuantityException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.UUID;

import static com.frandslund.freezermanagement.adapter.in.rest.common.ControllerCommons.clientErrorException;

// TODO: Add exception mapping
// TODO: Add logging to all endpoints

/**
 * @apiNote Primary REST adapter for interacting with the application. Could be one resource per use case.
 */
@Path("/freezers")
@Produces(MediaType.APPLICATION_JSON)
public class FreezerResource {
    private final CreateFreezerUseCase createFreezerUseCase;
    private final GetFreezerUseCase getFreezerUseCase;
    private final AddFreezerItemUseCase addFreezerItemUseCase;
    private final IncreaseFreezerItemQuantityUseCase increaseFreezerItemQuantityUseCase;
    private static final Logger LOG = LoggerFactory.getLogger(FreezerResource.class);

    public FreezerResource(CreateFreezerUseCase createFreezerUseCase, GetFreezerUseCase getFreezerUseCase, AddFreezerItemUseCase addFreezerItemUseCase, IncreaseFreezerItemQuantityUseCase increaseFreezerItemQuantityUseCase) {
        LOG.info("FreezerResource initialized");
        this.createFreezerUseCase = createFreezerUseCase;
        this.getFreezerUseCase = getFreezerUseCase;
        this.addFreezerItemUseCase = addFreezerItemUseCase;
        this.increaseFreezerItemQuantityUseCase = increaseFreezerItemQuantityUseCase;
    }

    @GET
    @Path("/{freezerId}")
    @APIResponse(responseCode = "200", description = "Get freezer by freezerId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FreezerWebModel.class)))
    @APIResponse(responseCode = "404", description = "Freezer does not exist for freezerId", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public FreezerWebModel getFreezer(@PathParam("freezerId") String freezerId) {
        try {
            Freezer freezer = getFreezerUseCase.getFreezer(new FreezerId(UUID.fromString(freezerId)));
            return FreezerWebModel.fromDomainModel(freezer);
        } catch (FreezerNotFoundException e) {
            throw clientErrorException(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    @GET
    @Path("/{userId}/{freezerName}")
    @APIResponse(responseCode = "200", description = "Get freezer by userId and freezerName", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FreezerWebModel.class)))
    @APIResponse(responseCode = "404", description = "Freezer does not exist for freezerId", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public FreezerWebModel getFreezerByUserIdAndFreezerName(@PathParam("userId") int userId, @PathParam("freezerName") String freezerName) {
        try {
            Freezer freezer = getFreezerUseCase.getFreezer(new UserId(userId), freezerName);
            return FreezerWebModel.fromDomainModel(freezer);
        } catch (FreezerNotFoundException e) {
            throw clientErrorException(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    @POST
    @APIResponse(responseCode = "201", description = "Freezer created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FreezerWebModel.class)))
    public Response createFreezer(CreateFreezerRequest createFreezerRequest) {
        LOG.info("Create freezer requested, freezerName='{}'", createFreezerRequest.freezerName());
        try {
            Freezer freezer = createFreezerUseCase.createFreezer(createFreezerRequest.userId(), createFreezerRequest.freezerName(), createFreezerRequest.shelfQuantity());
            FreezerWebModel freezerWebModel = FreezerWebModel.fromDomainModel(freezer);
            return Response
                    .created(URI.create("/freezers/" + freezer
                            .getFreezerId()
                            .freezerId()))
                    .entity(freezerWebModel)
                    .build();
        } catch (DuplicateFreezerNameException e) {
            // TODO: Not reached
            LOG.debug("DuplicateFreezerNameException caught", e);
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/{freezerId}/{shelfNumber}")
    @APIResponse(responseCode = "201", description = "Freezer item created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FreezerWebModel.class)))
    @APIResponse(responseCode = "404", description = "Freezer does not exist for freezerId", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response addFreezerItem(@PathParam("freezerId") String freezerId, @PathParam("shelfNumber") int shelfNumber, AddFreezerItemRequest addFreezerItemRequest) {
        try {
            Freezer freezer = addFreezerItemUseCase.addFreezerItemUseCase(new FreezerId(freezerId), shelfNumber, addFreezerItemRequest.itemName(), addFreezerItemRequest.quantity(), addFreezerItemRequest.description());
            FreezerWebModel freezerWebModel = FreezerWebModel.fromDomainModel(freezer);
            return Response
                    .created(URI.create("/freezers/" + freezer
                            .getFreezerId()
                            .freezerId()))
                    .entity(freezerWebModel)
                    .build();
        } catch (FreezerNotFoundException e) {
            throw clientErrorException(Response.Status.NOT_FOUND, e.getMessage());
        }
    }

    // TODO: Add OpenAPI documentation
    @PATCH
    @Path("/{freezerId}")
    @APIResponse(responseCode = "200", description = "Freezer item updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = FreezerWebModel.class)))
    @APIResponse(responseCode = "404", description = "Freezer does not exist for freezerId", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response increaseFreezerItemQuantity(@PathParam("freezerId") String freezerId, UpdateFreezerItemQuantityRequest updateFreezerItemQuantityRequest) {

        UUID freezerItemId = updateFreezerItemQuantityRequest.freezerItemId();
        int quantity = updateFreezerItemQuantityRequest.quantity();

        LOG.info("Increase freezer item quantity requested, freezerItemId={} quantity={}", freezerItemId, quantity);


        try {
            Freezer freezer = increaseFreezerItemQuantityUseCase.increaseFreezerItemQuantity(freezerId, updateFreezerItemQuantityRequest
                    .freezerItemId()
                    .toString(), quantity);
            FreezerWebModel freezerWebModel = FreezerWebModel.fromDomainModel(freezer);

            LOG.debug("Quantity of freezerItem increased, freezerItemId={}, quantity={}", freezerItemId, freezer
                    .getFreezerItem(new FreezerItemId(freezerItemId))
                    .getQuantity());

            return Response
                    .ok(URI.create("/freezers/" + freezer
                            .getFreezerId()
                            .freezerId()))
                    .entity(freezerWebModel)
                    .build();
        } catch (FreezerNotFoundException e) {
            LOG.debug("FreezerNotFoundException caught, errorMessage={}", e.getMessage());
            throw clientErrorException(Response.Status.NOT_FOUND, e.getMessage());
        } catch (InvalidFreezerItemQuantityException e) {
            LOG.debug("InvalidFreezerItemQuantityException caught, errorMessage={}", e.getMessage());
            throw clientErrorException(Response.Status.CONFLICT, e.getMessage());
        }
    }
}

