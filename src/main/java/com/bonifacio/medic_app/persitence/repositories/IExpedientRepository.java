package com.bonifacio.medic_app.persitence.repositories;

import com.bonifacio.medic_app.persitence.entities.ExpedientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IExpedientRepository extends JpaRepository<ExpedientEntity, UUID> {
}
