package com.bonifacio.medic_app.persitence.repositories;

import com.bonifacio.medic_app.persitence.entities.PatientEntity;
import com.bonifacio.medic_app.persitence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findByCurp(String username);
}
