package com.bonifacio.medic_app.mappers.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;

public interface IPatientMapper {
    PatientResponse patientToPatientResponse(PatientEntity patient);
}
