package com.frandslund.freezermanagement.adapter.in.rest;


import com.frandslund.freezermanagement.adapter.in.rest.dto.FreezerWebModel;
import com.frandslund.freezermanagement.model.freezer.Freezer;
import com.frandslund.freezermanagement.model.freezer.FreezerId;
import com.frandslund.freezermanagement.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.in.GetFreezerUseCase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.Instant;
import java.util.UUID;

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
    public FreezerWebModel createFreezer(@QueryParam("userId") int userId, @QueryParam("freezerName") String freezerName, @QueryParam("shelfQuantity") int shelfQuantity) {
        Freezer freezer = createFreezerUseCase.createFreezer(userId, freezerName, shelfQuantity);
        return FreezerWebModel.fromDomainModel(freezer);
    }

    @GET
    @Path("/{freezerId}")
    public FreezerWebModel getFreezer(@PathParam("freezerId") String freezerId) {
        Freezer freezer = getFreezerUseCase.getFreezer(new FreezerId(UUID.fromString(freezerId)));
        return FreezerWebModel.fromDomainModel(freezer);
    }

    // TODO: Add class: AddFreezerItemRequest
    @POST
    @Path("/{freezerId}")
    public FreezerWebModel addFreezerItem(@PathParam("freezerId") String freezerId, @QueryParam("shelfNumber") int shelfNumber, @QueryParam("itemName") String itemName, @QueryParam("quantity") int quantity, @QueryParam("description") String description) {
        //TODO: Add try/catch
        Freezer freezer = addFreezerItemUseCase.addFreezerItemUseCase(new FreezerId(freezerId), shelfNumber, itemName, quantity, description);
        return FreezerWebModel.fromDomainModel(freezer);
    }
}

