package com.bonifacio.medic_app.controller.dtos.expedient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class ExpedientRequest {
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String pulse;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String temperature;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String pressureSistolica;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    private String pressureDiastolica;
    @NotNull
    private UUID patientId;
    @NotNull
    private UUID userId;
}
