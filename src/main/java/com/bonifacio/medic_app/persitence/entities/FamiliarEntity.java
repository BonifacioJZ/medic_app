package com.bonifacio.medic_app.persitence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "familiars")
public class FamiliarEntity extends PersonEntity{

    @ManyToMany(mappedBy = "familiars")
    private List<PatientEntity> patients = new ArrayList<>();
}
