package com.bonifacio.medic_app.services.familiar;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.responses.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFamiliarService {
    Response<Page<FamiliarResponse>> getAll(Pageable pageable);
}
