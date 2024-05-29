package com.bonifacio.medic_app.controller.dtos.familiar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class FamiliarDetailResponse {
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
}
