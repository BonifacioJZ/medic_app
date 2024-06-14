package com.bonifacio.medic_app.services.user;

import com.bonifacio.medic_app.controller.dtos.user.UserResponse;
import com.bonifacio.medic_app.responses.Response;

public interface IUserService {
    Response<UserResponse> getUserByUsername(String username);
    Response<UserResponse> getUserByToken(String token);
}
