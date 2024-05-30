package com.bonifacio.medic_app.controller.dtos.familiar_patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class FamiliarPatientRequest {
    @NotEmpty
    @NotBlank
    private final String curpFamiliar;
    @Size(max = 3)
    @NotEmpty
    private final List<UUID> listPatients;
}
