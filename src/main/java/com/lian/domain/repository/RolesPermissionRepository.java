package com.lian.domain.repository;

import com.lian.domain.entity.RolesPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesPermissionRepository extends JpaRepository<RolesPermissionEntity, String> {
    List<RolesPermissionEntity> findByRoleName(String roleName);
}
