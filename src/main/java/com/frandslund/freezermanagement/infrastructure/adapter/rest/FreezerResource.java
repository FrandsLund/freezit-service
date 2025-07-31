package com.frandslund.freezermanagement.infrastructure.adapter.rest;


import com.frandslund.freezermanagement.application.FreezerApplicationService;
import com.frandslund.freezermanagement.domain.model.Freezer;
import com.frandslund.freezermanagement.domain.model.FreezerId;
import com.frandslund.freezermanagement.infrastructure.adapter.rest.dto.CreateFreezerResponseDto;
import com.frandslund.freezermanagement.infrastructure.adapter.rest.dto.FreezerDto;
import com.frandslund.freezermanagement.infrastructure.adapter.rest.mapper.FreezerMapper;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.List;
import java.util.UUID;

@Path("/freezers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FreezerResource {

    @Inject
    FreezerApplicationService freezerService;

    @Path("/{freezerId}")
    @GET
    public FreezerDto getFreezer(@PathParam("freezerId") String freezerId) {
        Freezer freezer = freezerService.getFreezer(new FreezerId(UUID.fromString(freezerId)));
        return FreezerMapper.toDto(freezer);
    }

    // TODO: Error in swagger (Can't pass json)
    @POST
    public Response createFreezer(@QueryParam("name") String name) {
        FreezerId freezerId = freezerService.createFreezer(name);

        CreateFreezerResponseDto responseDto = new CreateFreezerResponseDto(freezerId.id());


        return Response.created(UriBuilder.fromResource(FreezerResource.class).path(freezerId.id().toString()).build()).entity(responseDto) // Returnerer fryserens ID i body
                .build();
    }
}

