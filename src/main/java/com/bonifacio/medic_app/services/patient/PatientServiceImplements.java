package com.bonifacio.medic_app.services.patient;

import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.mappers.patient.IPatientMapper;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImplements implements IPatientService{
    
    @Autowired
    private final IPatientRepository patientRepository;
    @Autowired
    private final IPatientMapper  patientMapper;

    @Override
    public Response<List<PatientResponse>> getAll(Pageable pageable) {
        List<PatientResponse> data = this.patientRepository.findAll(pageable)
                .stream().map(this.patientMapper::patientToPatientResponse).toList();
        return new Response<>("pacientes",data,true);
    }

    @Override
    public Response<PatientResponse> save(PatientRequest patientRequest) {
        PatientEntity patient = this.patientMapper.patientRequestToPatient(patientRequest);
        patient = this.patientRepository.save(patient);
        PatientResponse patientResponse= this.patientMapper.patientToPatientResponse(patient);
        return new Response<>("paciente",patientResponse,true);
    }
}
