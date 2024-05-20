package com.bonifacio.medic_app.controller.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthRequest {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;    
}