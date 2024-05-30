package com.bonifacio.medic_app.services.familiar;

import com.bonifacio.medic_app.controller.FamiliarController;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarDetailResponse;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarRequest;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientResponse;
import com.bonifacio.medic_app.mappers.familiar.IFamiliarMapper;
import com.bonifacio.medic_app.mappers.patient.IPatientMapper;
import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import com.bonifacio.medic_app.persitence.repositories.IFamiliarRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Service
@AllArgsConstructor
public class FamiliarServiceImplement implements IFamiliarService{

    @Autowired
    private final IFamiliarRepository familiarRepository;
    @Autowired
    private final IFamiliarMapper familiarMapper;
    @Autowired
    private  final IPatientMapper patientMapper;

    @Override
    public Response<Page<FamiliarResponse>> getAll(Pageable pageable) {
        Page<FamiliarResponse> data = this.familiarRepository.findAll(pageable)
                .map(this.familiarMapper::familiarToFamiliarResponse);
        return new Response<>("familiares",data,true);
    }

    @Override
    public Response<FamiliarResponse> save(FamiliarRequest familiarRequest) {
        FamiliarEntity familiar = this.familiarMapper.familiarRequestToFamiliar(familiarRequest);
        familiar = this.familiarRepository.save(familiar);
        FamiliarResponse response = this.familiarMapper.familiarToFamiliarResponse(familiar);
        return new Response<>("Familiar",response,true);
    }

    @Override
    public Response<FamiliarDetailResponse> show(String curp) {
        FamiliarEntity familiar = this.familiarRepository.findByCurp(curp).orElse(null);
        if(familiar == null) return new Response<>("no existe",null,false);

        List<PatientResponse> patients = new ArrayList<>();
        if(!familiar.getPatients().isEmpty()) {
          patients = familiar.getPatients().stream().map(this.patientMapper::patientToPatientResponse).toList();
        }
        FamiliarDetailResponse familiarDetail = this.familiarMapper.familiarToFamiliarDetailsResponse(familiar,patients);

        return  new Response<>("Familiar",familiarDetail,true);
    }

    @Override
    public Response<FamiliarResponse> edit(String Curp, FamiliarRequest familiarRequest) {
        FamiliarEntity oldFamiliar = this.familiarRepository.findByCurp(Curp).orElse(null);
        if(oldFamiliar==null) return new Response<>("No encontrado",null,false);

        oldFamiliar = this.familiarMapper.updateFamiliar(oldFamiliar,familiarRequest);
        oldFamiliar = this.familiarRepository.save(oldFamiliar);

        FamiliarResponse response = this.familiarMapper.familiarToFamiliarResponse(oldFamiliar);
        return new Response<>("Familiar", response,true);
    }

    @Override
    public void delete(UUID uuid) {
        this.familiarRepository.deleteById(uuid);
    }
}
