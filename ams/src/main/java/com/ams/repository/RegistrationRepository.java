package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    Registration findByTeacherid(int teacherid);
    Registration findByGvpemail(String gvpemail);
    Optional<Registration> findBygvpemail(String email);
}
