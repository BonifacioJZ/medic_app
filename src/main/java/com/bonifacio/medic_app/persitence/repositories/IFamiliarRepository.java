package com.bonifacio.medic_app.persitence.repositories;

import com.bonifacio.medic_app.persitence.entities.FamiliarEntity;
import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFamiliarRepository extends JpaRepository<FamiliarEntity, UUID> {
    Optional<FamiliarEntity> findByCurp(String curp);
    boolean existsByCurp(String curp);

}
