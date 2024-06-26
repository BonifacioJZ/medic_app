package com.bonifacio.medic_app.controller.dtos.patient;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PatientDetailsResponse {
    private final UUID id;
    private final String name;
    private final String lastName;
    private final String phone;
    private final String email;
    private final String colony;
    private final String city;
    private final LocalDate birthday;
    private final String address;
    private final String curp;
    private final int age;
    private final List<FamiliarResponse> familiars;
}
