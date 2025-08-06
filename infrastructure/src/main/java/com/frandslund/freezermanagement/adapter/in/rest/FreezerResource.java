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

    public FreezerResource(CreateFreezerUseCase createFreezerUseCase, GetFreezerUseCase getFreezerUseCase, AddFreezerItemUseCase addFreezerItemUseCase) {
        this.createFreezerUseCase = createFreezerUseCase;
        this.getFreezerUseCase = getFreezerUseCase;
        this.addFreezerItemUseCase = addFreezerItemUseCase;
    }

    @POST
    public FreezerWebModel createFreezer(CreateFreezerRequest createFreezerRequest) {
        Freezer freezer = createFreezerUseCase.createFreezer(createFreezerRequest.userId(), createFreezerRequest.freezerName(), createFreezerRequest.shelfQuantity());
        return FreezerWebModel.fromDomainModel(freezer);
    }

    @GET
    @Path("/{freezerId}")
    public FreezerWebModel getFreezer(@PathParam("freezerId") String freezerId) {
        Freezer freezer = getFreezerUseCase.getFreezer(new FreezerId(UUID.fromString(freezerId)));
        return FreezerWebModel.fromDomainModel(freezer);
    }

    @POST
    @Path("/{freezerId}/{shelfNumber}")
    public FreezerWebModel addFreezerItem(@PathParam("freezerId") String freezerId, @PathParam("shelfNumber") int shelfNumber, AddFreezerItemRequest addFreezerItemRequest) {
        try {
            Freezer freezer = addFreezerItemUseCase.addFreezerItemUseCase(new FreezerId(freezerId), shelfNumber, addFreezerItemRequest.itemName(), addFreezerItemRequest.quantity(), addFreezerItemRequest.description());
            return FreezerWebModel.fromDomainModel(freezer);
        } catch (NoSuchElementException e) {
            throw clientErrorException(
                    Response.Status.BAD_REQUEST, "The requested freezer does not exist");
        }
    }
}

