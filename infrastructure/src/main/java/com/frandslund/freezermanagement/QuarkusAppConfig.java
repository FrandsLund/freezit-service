package com.frandslund.freezermanagement;

import com.frandslund.freezermanagement.adapter.out.event.FreezerEventPublisher;
import com.frandslund.freezermanagement.port.in.AddFreezerItemUseCase;
import com.frandslund.freezermanagement.port.in.CreateFreezerUseCase;
import com.frandslund.freezermanagement.port.in.GetFreezerUseCase;
import com.frandslund.freezermanagement.port.out.persistence.FreezerRepository;
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
    Instance<FreezerRepository> freezerRepository;

    @Inject
    FreezerEventPublisher freezerEventPublisher;

    @Produces
    @ApplicationScoped
    GetFreezerUseCase getFreezerUseCase() {
        return new GetFreezerService(freezerRepository.get());
    }

    @Produces
    @ApplicationScoped
    CreateFreezerUseCase createFreezerUseCase() {
        return new CreateFreezerService(freezerRepository.get(), freezerEventPublisher);
    }

    @Produces
    @ApplicationScoped
    AddFreezerItemUseCase addFreezerItemUseCase() {
        return new AddFreezerItemService(freezerRepository.get());
    }


}
