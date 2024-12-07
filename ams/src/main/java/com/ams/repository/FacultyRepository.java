package com.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ams.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    Faculty findByFacultyid(int facultyId);
}
