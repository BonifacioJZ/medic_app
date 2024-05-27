package com.bonifacio.medic_app.mappers.familiar;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import org.springframework.stereotype.Component;

@Component
public class FamiliarMapperImplements implements IFamiliarMapper {
    @Override
    public FamiliarResponse familiarToFamiliarResponse(FamiliarEntity familiar) {
        if(familiar ==null) return null;

        return FamiliarResponse.builder()
                .id(familiar.getId())
                .name(familiar.getName())
                .lastName(familiar.getLastName())
                .email(familiar.getEmail())
                .phone(familiar.getPhone())
                .city(familiar.getCity())
                .address(familiar.getAddress())
                .colony(familiar.getColony())
                .curp(familiar.getCurp())
                .age(familiar.getAge())
                .birthday(familiar.getBirthday())
                .build();
    }
}
