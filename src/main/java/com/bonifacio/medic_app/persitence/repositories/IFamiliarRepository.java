package com.bonifacio.medic_app.persitence.repositories;

import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IFamiliarRepository extends JpaRepository<FamiliarEntity, UUID> {
    Optional<FamiliarEntity> findByCurp(String curp);

}
