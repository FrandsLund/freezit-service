package com.frandslund.freezermanagement;

import com.frandslund.freezermanagement.adapter.out.event.FreezerEventPublisherAdapter;
import com.frandslund.freezermanagement.port.in.freezer.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.in.freezer.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.in.freezer.GetFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.freezer.FreezerRepositoryPort;
import com.frandslund.freezermanagement.service.freezer.CreateFreezerService;
import com.frandslund.freezermanagement.service.freezer.GetFreezerService;
import com.frandslund.freezermanagement.service.freezeritem.AddFreezerItemService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

class QuarkusAppConfig {

    // TODO: Try and remove this instance annotation
    @Inject
    Instance<FreezerRepositoryPort> freezerRepositoryPort;

    @Inject
    FreezerEventPublisherAdapter freezerEventPublisherAdapter;

    @Produces
    @ApplicationScoped
    GetFreezerUseCase getFreezerUseCase() {
        return new GetFreezerService(freezerRepositoryPort.get());
    }

    @Produces
    @ApplicationScoped
    CreateFreezerUseCase createFreezerUseCase() {
        return new CreateFreezerService(freezerRepositoryPort.get(), freezerEventPublisherAdapter);
    }

    @Produces
    @ApplicationScoped
    AddFreezerItemUseCase addFreezerItemUseCase() {
        return new AddFreezerItemService(freezerRepositoryPort.get(), freezerEventPublisherAdapter);
    }


}
