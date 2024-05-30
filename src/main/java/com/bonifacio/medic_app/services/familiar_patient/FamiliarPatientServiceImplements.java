package com.bonifacio.medic_app.services.familiar_patient;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarDetailResponse;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.controller.dtos.familiar_patient.FamiliarPatientRequest;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.mappers.familiar.IFamiliarMapper;
import com.bonifacio.medic_app.mappers.patient.IPatientMapper;
import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.repositories.IFamiliarRepository;
import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import com.bonifacio.medic_app.responses.Response;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FamiliarPatientServiceImplements implements IFamiliarPatientService {
    @Autowired
    private final IFamiliarRepository familiarRepository;
    @Autowired
    private final IFamiliarMapper familiarMapper;
    @Autowired
    private final IPatientRepository patientRepository;
    @Autowired
    private final IPatientMapper patientMapper;

    @Override
    @Transactional
    public Response<FamiliarDetailResponse> addPatients(FamiliarPatientRequest familiarPatientRequest) {
        FamiliarEntity familiar = this.familiarRepository.findByCurp(familiarPatientRequest.getCurpFamiliar())
                .orElse(null);
        if(familiar == null) return new Response<>("No se encontro",null,false);

        List<PatientEntity> patients = this.patientRepository.findAllById(familiarPatientRequest.getListPatients());

        if (patients.isEmpty()) return new Response<>("no existen",null,false);

        familiar.setPatients(patients);
        familiar = this.familiarRepository.save(familiar);

        List<PatientResponse> patientResponses = familiar.getPatients().stream()
                .map(this.patientMapper::patientToPatientResponse).toList();
        FamiliarDetailResponse response = this.familiarMapper.familiarToFamiliarDetailsResponse(familiar,patientResponses);

        return new Response<>("Familiar",response,true);
    }
}
