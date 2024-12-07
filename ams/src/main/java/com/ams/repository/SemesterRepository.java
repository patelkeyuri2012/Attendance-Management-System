package com.ams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    Semester findBySemesterid(int semesterid);
    List<Semester> findAll();
	List<Semester> findAllByActive(boolean active);
	
	@Query(value = "SELECT s.* FROM semester s JOIN program p ON s.semesterid <= p.semesterid WHERE p.programid = :programId AND s.active = true", nativeQuery = true)
	List<Semester> findAllByProgram(@Param("programId") int programId);

}