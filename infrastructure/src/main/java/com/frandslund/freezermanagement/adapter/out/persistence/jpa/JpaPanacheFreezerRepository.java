package com.frandslund.freezermanagement.adapter.out.persistence.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class JpaPanacheFreezerRepository implements PanacheRepositoryBase<FreezerEntity, UUID> {



}
