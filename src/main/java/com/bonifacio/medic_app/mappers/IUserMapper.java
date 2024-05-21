package com.bonifacio.medic_app.mappers;

import com.bonifacio.medic_app.controller.dtos.auth.AuthCreateUserRequest;
import com.bonifacio.medic_app.persitence.entities.UserEntity;

public interface IUserMapper {
    UserEntity authCreateUserToUserEntity(AuthCreateUserRequest user);   
}