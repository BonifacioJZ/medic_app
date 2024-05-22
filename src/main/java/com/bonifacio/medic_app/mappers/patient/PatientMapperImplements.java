package com.bonifacio.medic_app.mappers.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientMapperImplements implements IPatientMapper {
    @Override
    public PatientResponse patientToPatientResponse(PatientEntity patient) {
        if(patient==null) return null;
        return PatientResponse.builder()
                .id(patient.getId())
                .name(patient.getName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .curp(patient.getCurp())
                .phone(patient.getPhone())
                .age(patient.getAge())
                .city(patient.getCity())
                .address(patient.getAddress())
                .colony(patient.getColony())
                .address(patient.getAddress())
                .birthday(patient.getBirthday())
                .build();
    }
}
