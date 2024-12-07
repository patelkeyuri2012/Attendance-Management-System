package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.Registration;

public interface HODRepository extends JpaRepository<Registration, Integer> {
    Registration findByTeacherid(int teacherid);
    Optional<Registration> findBygvpemail(String email);
   
}
