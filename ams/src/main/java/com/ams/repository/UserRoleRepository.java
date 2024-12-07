package com.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
}
