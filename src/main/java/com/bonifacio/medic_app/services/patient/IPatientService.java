package com.bonifacio.medic_app.services.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.responses.Response;

import java.util.List;
public interface IPatientService  {

    Response<List<PatientResponse>> getAll();

}
