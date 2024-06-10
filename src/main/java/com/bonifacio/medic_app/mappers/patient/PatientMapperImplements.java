package com.bonifacio.medic_app.mappers.patient;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.List;
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
                .name(patientRequest.getName().toLowerCase())
                .lastName(patientRequest.getLastName().toLowerCase())
                .email(patientRequest.getEmail())
                .phone((StringUtils.isAllEmpty(patientRequest.getPhone())?"000-000-0000":patientRequest.getPhone()))
                .address(patientRequest.getAddress().toLowerCase())
                .birthday(patientRequest.getBirthday())
                .city(patientRequest.getCity().toLowerCase())
                .colony(patientRequest.getColony().toLowerCase())
                .curp(patientRequest.getCurp().toUpperCase())
                .build();

    }

    @Override
    public PatientDetailsResponse patientToPatientDetails(PatientEntity patient, List<FamiliarResponse> familiarResponseList) {
        if(patient==null) return null;

        return PatientDetailsResponse.builder()
                .id(patient.getId())
                .curp(patient.getCurp())
                .name(patient.getName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .colony(patient.getColony())
                .city(patient.getCity())
                .birthday(patient.getBirthday())
                .age(patient.getAge())
                .familiars(familiarResponseList)
                .build();

    }

    @Override
    public PatientEntity updatePatient(PatientRequest patientRequest, PatientEntity patient) {
        if(patientRequest==null) return null;
        patient.setName((StringUtils.isEmpty(patientRequest.getName()))? patient.getName() : patientRequest.getName());
        patient.setLastName((StringUtils.isEmpty(patientRequest.getLastName()))?patient.getLastName(): patientRequest.getLastName());
        patient.setPhone((StringUtils.isEmpty(patientRequest.getPhone()))? patient.getPhone() : patientRequest.getPhone());
        patient.setEmail((StringUtils.isEmpty(patientRequest.getEmail()))?patient.getEmail():patientRequest.getEmail());
        patient.setAddress((StringUtils.isEmpty(patientRequest.getAddress()))?patient.getAddress():patientRequest.getAddress());
        patient.setCity((StringUtils.isEmpty(patientRequest.getCity()))? patient.getCity() : patientRequest.getCity());
        patient.setColony((StringUtils.isEmpty(patientRequest.getColony()))?patient.getColony():patientRequest.getColony());
        patient.setBirthday((patientRequest.getBirthday()==null)?patient.getBirthday():patientRequest.getBirthday());
        patient.setCurp((StringUtils.isEmpty(patientRequest.getCurp()))? patient.getCurp() : patientRequest.getCurp());

        return patient;
    }
}
