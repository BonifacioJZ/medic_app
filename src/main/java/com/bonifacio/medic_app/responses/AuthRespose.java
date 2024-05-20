package com.bonifacio.medic_app.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@JsonPropertyOrder({"username", "message", "status", "jwt"})
public class AuthRespose {
    private final String username;
    private final String message;
    private final String jwt;
    private final boolean status;
}