package com.bonifacio.medic_app.mappers;

import com.bonifacio.medic_app.controller.dtos.user.UserResponse;
import org.springframework.stereotype.Component;

import com.bonifacio.medic_app.controller.dtos.auth.AuthCreateUserRequest;
import com.bonifacio.medic_app.persitence.entities.UserEntity;

@Component
public class UserMapperImplements implements IUserMapper{

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
        .city(user.getCity().toLowerCase())
        .curp(user.getCurp().toUpperCase())
        .phone(user.getPhone().toLowerCase())
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .isEnabled(true)
        .build();
    }

    @Override
    public UserResponse userToUserResponse(UserEntity user) {
        if(user==null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .city(user.getCity())
                .colony(user.getColony())
                .address(user.getAddress())
                .curp(user.getCurp())
                .phone(user.getPhone())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .build();
    }

}