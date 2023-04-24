package com.backEnd_w5_project.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEnd_w5_project.auth.entity.ERole;
import com.backEnd_w5_project.auth.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
