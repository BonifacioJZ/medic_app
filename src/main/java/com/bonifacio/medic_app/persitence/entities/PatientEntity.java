package com.bonifacio.medic_app.persitence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "patients")
public class PatientEntity extends PersonEntity{

    @ManyToMany(targetEntity = FamiliarEntity.class,cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    },fetch = FetchType.LAZY)
    @JoinTable(name = "patient_familiar", joinColumns = { @JoinColumn(name = "fk_patient"), }, inverseJoinColumns = {
            @JoinColumn(name = "fk_familiar") })
    @JsonIgnore
    private List<FamiliarEntity> familiars;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="patient_id")
    private List<ExpedientEntity> expedients;
}
