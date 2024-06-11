package com.bonifacio.medic_app.mappers.expedient;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientResponse;
import com.bonifacio.medic_app.persitence.entities.ExpedientEntity;

public interface IExpedientMapper {
    ExpedientResponse expedientToExpedientResponse(ExpedientEntity expedient);
}
