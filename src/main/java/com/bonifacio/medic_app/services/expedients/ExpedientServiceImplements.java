package com.bonifacio.medic_app.services.expedients;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientRequest;
import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientResponse;
import com.bonifacio.medic_app.mappers.expedient.IExpedientMapper;
import com.bonifacio.medic_app.persitence.entities.ExpedientEntity;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.entities.UserEntity;
import com.bonifacio.medic_app.persitence.repositories.IExpedientRepository;
import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ExpedientServiceImplements implements IExpedientService{
    @Autowired
    private final IExpedientRepository expedientRepository;
    @Autowired
    private final IExpedientMapper expedientMapper;
    @Autowired
    private final IUserRepository userRepository;
    @Autowired
    private final IPatientRepository patientRepository;

    @Override
    public Response<Page<ExpedientResponse>> getAll(Pageable pageable) {
        Page<ExpedientResponse>  data = this.expedientRepository.findAll(pageable)
                .map(this.expedientMapper::expedientToExpedientResponse);

        return new Response<>("Expedientes",data,true);
    }

    @Override
    public Response<ExpedientResponse> save(ExpedientRequest expedientRequest) {
        try{
            UserEntity user = this.userRepository.findById(expedientRequest.getUserId()).orElse(null);
            if(user==null) return new Response<>("No existe el usuario con id ".concat(expedientRequest.getUserId().toString()),
                    null,false);

            PatientEntity patient = this.patientRepository.findById(expedientRequest.getPatientId()).orElse(null);
            if(patient==null) return new Response<>("No existe el paciente con id ".concat(expedientRequest.getPatientId().toString()),
                    null,false);

            ExpedientEntity expedient = this.expedientMapper.expedientRequestToExpedientEntity(expedientRequest);
            expedient.setPatient(patient);
            expedient.setUser(user);
            expedient = this.expedientRepository.save(expedient);

            ExpedientResponse expedientResponse = this.expedientMapper.expedientToExpedientResponse(expedient);
            return new Response<>("Expediente creado",expedientResponse,true);
        }catch (Exception e){
            return new Response<>(e.getMessage(),null,false);
        }
    }

    @Override
    public Response<ExpedientResponse> findById(UUID id) {
        ExpedientEntity expedient = this.expedientRepository.findById(id).orElse(null);
        if(expedient == null) return new Response<>("No existe el expediente con id ".concat(id.toString()),
                null,false);

        ExpedientResponse response = this.expedientMapper.expedientToExpedientResponse(expedient);
        return new Response<>("expediente con id ".concat(id.toString()),response,true);
    }
}
