package com.bonifacio.medic_app.persitence.repositories;

import com.bonifacio.medic_app.persitence.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPermissionRepository extends JpaRepository<PermissionEntity, UUID> {
}
