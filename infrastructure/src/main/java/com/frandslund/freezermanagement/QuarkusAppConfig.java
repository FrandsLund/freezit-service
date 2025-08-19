package com.frandslund.freezermanagement;

import com.frandslund.freezermanagement.adapter.out.event.FreezerEventPublisherAdapter;
import com.frandslund.freezermanagement.freezer.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.freezer.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.in.GetFreezerUseCase;
import com.frandslund.freezermanagement.freezer.port.out.persistence.FreezerRepositoryPort;
import com.frandslund.freezermanagement.freezer.service.CreateFreezerService;
import com.frandslund.freezermanagement.freezer.service.GetFreezerService;
import com.frandslund.freezermanagement.freezer.service.AddFreezerItemService;
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
