package com.bonifacio.medic_app.services.familiar_patient;

import com.bonifacio.medic_app.controller.dtos.familiar_patient.PatientFamiliarRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.responses.Response;

public interface IPatientFamiliarService {
    Response<PatientDetailsResponse> addFamiliar(PatientFamiliarRequest patientFamiliarRequest);
}
