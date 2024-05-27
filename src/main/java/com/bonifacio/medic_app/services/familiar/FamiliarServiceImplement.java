package com.bonifacio.medic_app.services.familiar;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.mappers.familiar.IFamiliarMapper;
import com.bonifacio.medic_app.persitence.repositories.IFamiliarRepository;
import com.bonifacio.medic_app.responses.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FamiliarServiceImplement implements IFamiliarService{

    @Autowired
    private final IFamiliarRepository familiarRepository;
    @Autowired
    private final IFamiliarMapper familiarMapper;

    @Override
    public Response<Page<FamiliarResponse>> getAll(Pageable pageable) {
        Page<FamiliarResponse> data = this.familiarRepository.findAll(pageable)
                .map(this.familiarMapper::familiarToFamiliarResponse);
        return new Response<>("familiares",data,true);
    }
}
