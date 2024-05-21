package com.bonifacio.medic_app.mappers;

import org.springframework.stereotype.Component;

import com.bonifacio.medic_app.controller.dtos.auth.AuthCreateUserRequest;
import com.bonifacio.medic_app.persitence.entities.UserEntity;

@Component
public class UserMapperImplemets implements IUserMapper{

    @Override
    public UserEntity authCreateUserToUserEntity(AuthCreateUserRequest user) {
        if(user==null) return null;

        return UserEntity.builder()
        .name(user.getName().toLowerCase())
        .lastName(user.getLastName().toLowerCase())
        .username(user.getUsername().toLowerCase())
        .email(user.getEmail().toLowerCase())
        .address(user.getAddress().toLowerCase())
        .birthday(user.getBirthday())
        .colony(user.getColony().toLowerCase())
        .city(user.getColony().toLowerCase())
        .curp(user.getCurp().toLowerCase())
        .phone(user.getPhone().toLowerCase())
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnabled(true)
        .build();
    }
    
}