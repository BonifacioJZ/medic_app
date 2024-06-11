package com.bonifacio.medic_app.services.expedients;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientResponse;
import com.bonifacio.medic_app.mappers.expedient.IExpedientMapper;
import com.bonifacio.medic_app.persitence.repositories.IExpedientRepository;
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

    @Override
    public Response<Page<ExpedientResponse>> getAll(Pageable pageable) {
        Page<ExpedientResponse>  data = this.expedientRepository.findAll(pageable)
                .map(this.expedientMapper::expedientToExpedientResponse);

        return new Response<>("Expedientes",data,true);
    }
}
