package com.bonifacio.medic_app.controller.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class UserResponse {
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
    private final Collection<? extends GrantedAuthority> authorities;
}
