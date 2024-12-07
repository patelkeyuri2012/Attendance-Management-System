package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByAdminid(int adminid); 
    Admin findByEmail(String email);
    Optional<Admin> findByemail(String email);
}
