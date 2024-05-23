package com.bonifacio.medic_app.controller.dtos.auth;

import java.time.LocalDate;

import com.bonifacio.medic_app.controller.dtos.role.AuthCreateRole;

import com.bonifacio.medic_app.validations.CurpUniqueUser;
import com.bonifacio.medic_app.validations.EmailUnique;
import com.bonifacio.medic_app.validations.UniqueUsername;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthCreateUserRequest {
    
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String name;
    @Size(max = 150)
    @NotBlank
    @NotEmpty
    private String lastName;
    @NotBlank
    @NotEmpty
    @Size(max = 13, min = 10)
    private String phone;
    @Email
    @EmailUnique
    private String email;
    @Size(max = 200)
    @NotEmpty
    private String colony;
    @Size(max = 200)
    @NotBlank
    @NotEmpty
    private String city;
    @NotNull
    private LocalDate birthday;
    @NotBlank
    @NotEmpty
    private String address;
    @Size(max = 18, min = 18)
    @NotEmpty
    @NotBlank
    @CurpUniqueUser
    private String curp;
    @NotBlank
    @NotEmpty
    @UniqueUsername
    @Size(min = 8,max = 32)
    private String username;
    @NotEmpty
    @NotBlank
    @Size(min = 8,max = 32)
    private String password;
    @Valid
    private AuthCreateRole roles;
}