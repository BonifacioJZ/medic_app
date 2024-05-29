package com.bonifacio.medic_app.mappers.familiar;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarDetailResponse;
import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarRequest;
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

    @Override
    public FamiliarEntity familiarRequestToFamiliar(FamiliarRequest familiarRequest) {
        if(familiarRequest==null) return null;

        return FamiliarEntity
                .builder()
                .name(familiarRequest.getName())
                .lastName(familiarRequest.getLastName())
                .email(familiarRequest.getEmail())
                .phone(familiarRequest.getPhone())
                .city(familiarRequest.getCity())
                .colony(familiarRequest.getColony())
                .address(familiarRequest.getAddress())
                .curp(familiarRequest.getCurp())
                .birthday(familiarRequest.getBirthday())
                .build();
    }

    @Override
    public FamiliarDetailResponse familiarToFamiliarDetailsResponse(FamiliarEntity familiar) {
        if(familiar==null) return null;


        return FamiliarDetailResponse.builder()
                .id(familiar.getId())
                .name(familiar.getName())
                .lastName(familiar.getLastName())
                .email(familiar.getEmail())
                .phone(familiar.getPhone())
                .city(familiar.getCity())
                .colony(familiar.getColony())
                .address(familiar.getAddress())
                .birthday(familiar.getBirthday())
                .curp(familiar.getCurp())
                .age(familiar.getAge())
                .build();
    }
}
