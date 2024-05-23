package com.bonifacio.medic_app.services.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.responses.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface IPatientService  {

    Response<List<PatientResponse>> getAll(Pageable pageable);
    Response<PatientResponse> save(PatientRequest patientRequest);
}
