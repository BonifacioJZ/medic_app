package com.bonifacio.medic_app.persitence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "expedients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExpedientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    @Column()
    private String pulse;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    @Column()
    private String temperature;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    @Column(name = "pressure_sistolica")
    private String pressureSistolica;
    @NotEmpty
    @NotBlank
    @Size(max = 100)
    @Column(name = "pressure_diastolica")
    private String pressureDiastolica;
    @ManyToOne(targetEntity = PatientEntity.class)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column
    private LocalDate date;
    @Column
    private LocalTime time;
    @CreationTimestamp
    @Column(name = "create_at")
    private Instant createAt;
    @UpdateTimestamp
    @Column(name = "update_at")
    private Instant updateAt;
}
