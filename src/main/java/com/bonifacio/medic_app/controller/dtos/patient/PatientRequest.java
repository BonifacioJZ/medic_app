package com.bonifacio.medic_app.controller.dtos.patient;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PatientRequest {
    @Size(max = 100)
    @NotEmpty
    @NotBlank
    private  String name;
    @Size(max = 150)
    @NotBlank
    @NotEmpty
    private String lastName;
    @NotEmpty
    @NotBlank
    @Size(max = 13, min = 10)
    private String phone;
    @Email
    private String email;
    @Size(max = 200)
    @NotBlank
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
    @NotBlank
    @NotEmpty
    private String curp;
}
