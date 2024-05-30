package com.bonifacio.medic_app.services.familiar_patient;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarDetailResponse;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.controller.dtos.familiar_patient.FamiliarPatientRequest;
import com.bonifacio.medic_app.responses.Response;

public interface IFamiliarPatientService {
    Response<FamiliarDetailResponse> addPatients(FamiliarPatientRequest familiarPatientRequest);
}
