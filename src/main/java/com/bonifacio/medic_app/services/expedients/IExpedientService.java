package com.bonifacio.medic_app.services.expedients;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientResponse;
import com.bonifacio.medic_app.responses.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IExpedientService {
    Response<Page<ExpedientResponse>> getAll( Pageable pageable);
}
