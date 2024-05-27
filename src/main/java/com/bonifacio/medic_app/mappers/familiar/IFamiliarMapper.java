package com.bonifacio.medic_app.mappers.familiar;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;

public interface IFamiliarMapper {
    FamiliarResponse familiarToFamiliarResponse(FamiliarEntity familiar);
}
