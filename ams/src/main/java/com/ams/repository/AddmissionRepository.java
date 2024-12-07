package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ams.entity.Addmission;
import com.ams.entity.Student;

public interface AddmissionRepository extends JpaRepository<Addmission, Integer> {

	Addmission findByStudentid(int studentid);

	@Query("SELECT s FROM Student s WHERE s.studentid = :studentid")
	Student fetchStudentByStudentid(int studentid);

	Addmission findByEmail(String email);
	Optional<Addmission> findBygvpemail(String email);
}
