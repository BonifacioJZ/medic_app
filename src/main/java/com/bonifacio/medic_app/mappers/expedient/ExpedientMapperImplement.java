package com.bonifacio.medic_app.mappers.expedient;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientResponse;
import com.bonifacio.medic_app.persitence.entities.ExpedientEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpedientMapperImplement implements IExpedientMapper {
    @Override
    public ExpedientResponse expedientToExpedientResponse(ExpedientEntity expedient) {
        if(expedient == null) return null;

        return ExpedientResponse.builder()
                .id(expedient.getId())
                .pulse(expedient.getPulse())
                .time(expedient.getTime())
                .date(expedient.getDate())
                .pressureSistolica(expedient.getPressureSistolica())
                .pressureDiastolica(expedient.getPressureDiastolica())
                .temperature(expedient.getTemperature())
                .PatientName(expedient.getPatient().fullName())
                .patientId(expedient.getPatient().getId())
                .userId(expedient.getUser().getId())
                .userName(expedient.getUser().fullName())
                .build();
    }
}
