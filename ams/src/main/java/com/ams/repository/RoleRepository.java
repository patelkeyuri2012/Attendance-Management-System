package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.Role;
import com.ams.entity.UserRole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

	Optional<UserRole> findByRoleid(Role roleId);
}