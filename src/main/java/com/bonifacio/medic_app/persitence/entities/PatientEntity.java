package com.bonifacio.medic_app.persitence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "patients")
public class PatientEntity extends PersonEntity{

    @ManyToMany(targetEntity = FamiliarEntity.class,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.EAGER)
    @JoinTable(name = "patient_familiar", joinColumns = { @JoinColumn(name = "fk_patient"), }, inverseJoinColumns = {
            @JoinColumn(name = "fk_familiar") })
    @JsonIgnore
    private List<FamiliarEntity> familiars = new ArrayList<>();
}
