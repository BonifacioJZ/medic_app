package com.bonifacio.medic_app.services.familiar_patient;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarRequest;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.controller.dtos.familiar_patient.PatientFamiliarRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.mappers.familiar.IFamiliarMapper;
import com.bonifacio.medic_app.mappers.patient.IPatientMapper;
import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.repositories.IFamiliarRepository;
import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientFamiliarServiceImplements implements IPatientFamiliarService {
    @Autowired
    private final IFamiliarRepository familiarRepository;
    @Autowired
    private final IFamiliarMapper familiarMapper;
    @Autowired
    private final IPatientRepository patientRepository;
    @Autowired
    private final IPatientMapper patientMapper;

    @Override
    public Response<PatientDetailsResponse> addFamiliar(PatientFamiliarRequest patientFamiliarRequest) {
        PatientEntity patient = this.patientRepository.findByCurp(patientFamiliarRequest.getCurpPatient())
                .orElse(null);

        if(patient ==null) return new Response<>("No esta registrado el paciente",null,false);
        List<FamiliarEntity> familiars = this.familiarRepository.findAllById(patientFamiliarRequest.getFamiliars());

        patient.setFamiliars(familiars);
        patient = this.patientRepository.save(patient);

        List<FamiliarResponse> familiarResponseList = patient.getFamiliars().stream()
                .map(this.familiarMapper::familiarToFamiliarResponse).toList();
        PatientDetailsResponse data  = this.patientMapper.patientToPatientDetails(patient,familiarResponseList);

        return new Response<>("paciente",data,true);
    }
}
