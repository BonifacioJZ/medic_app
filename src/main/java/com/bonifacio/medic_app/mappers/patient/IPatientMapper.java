package com.bonifacio.medic_app.mappers.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;

public interface IPatientMapper {
    PatientResponse patientToPatientResponse(PatientEntity patient);
    PatientEntity patientRequestToPatient(PatientRequest patientRequest);
    PatientDetailsResponse patientToPatientDetails(PatientEntity patient);
    PatientEntity updatePatient(PatientRequest patientRequest,PatientEntity patient);
}
