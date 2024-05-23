package com.bonifacio.medic_app.mappers.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public PatientEntity patientRequestToPatient(PatientRequest patientRequest) {
        if(patientRequest==null) return null;
        return PatientEntity.builder()
                .name(patientRequest.getName())
                .lastName(patientRequest.getLastName())
                .email(patientRequest.getEmail())
                .phone((StringUtils.isAllEmpty(patientRequest.getPhone())?"000-000-0000":patientRequest.getPhone()))
                .address(patientRequest.getAddress())
                .birthday(patientRequest.getBirthday())
                .city(patientRequest.getCity())
                .colony(patientRequest.getColony())
                .curp(patientRequest.getCurp())
                .build();

    }
}
