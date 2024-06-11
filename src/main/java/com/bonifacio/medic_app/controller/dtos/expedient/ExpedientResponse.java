package com.bonifacio.medic_app.controller.dtos.expedient;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class ExpedientResponse {
    private UUID id;
    private String pulse;
    private String temperature;
    private String pressureSistolica;
    private String pressureDiastolica;
    private UUID patientId;
    private String PatientName;
    private UUID userId;
    private String userName;
    private LocalDate date;
    private LocalTime time;
}
