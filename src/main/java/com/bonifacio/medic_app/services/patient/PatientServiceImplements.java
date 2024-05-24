package com.bonifacio.medic_app.services.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.mappers.patient.IPatientMapper;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImplements implements IPatientService{
    
    @Autowired
    private final IPatientRepository patientRepository;
    @Autowired
    private final IPatientMapper  patientMapper;

    @Override
    public Response<Page<PatientResponse>> getAll(Pageable pageable) {
        try {
            Page<PatientResponse> data= this.patientRepository.findAll(pageable).map(this.patientMapper::patientToPatientResponse);
            return new Response<>("pacientes",data,true);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Response<PatientResponse> save(PatientRequest patientRequest) {
        PatientEntity patient = this.patientMapper.patientRequestToPatient(patientRequest);
        patient = this.patientRepository.save(patient);
        PatientResponse patientResponse= this.patientMapper.patientToPatientResponse(patient);
        return new Response<>("paciente",patientResponse,true);
    }

    @Override
    public Response<PatientDetailsResponse> getByCurp(String curp) {
        PatientEntity patient = this.patientRepository.findByCurp(curp).orElse(null);

        PatientDetailsResponse patientDetailsResponse = this.patientMapper.patientToPatientDetails(patient);
        return  new Response<>("Paciente",patientDetailsResponse,true);
    }

    @Override
    public Response<PatientResponse> update(UUID id, PatientRequest patientRequest) {
        return null;
    }
}
