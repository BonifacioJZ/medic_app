package com.bonifacio.medic_app.controller.dtos.role;

import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthCreateRole {
    @Size(max = 3)
    List<String> roles;
}