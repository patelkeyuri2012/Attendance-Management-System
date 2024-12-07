package com.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByTeacherid(int teacherId);

	Teacher findByEmail(String email);

	List<Program> getProgramsByteacherid(int teacherId);

	List<Semester> getSemestersByteacherid(int teacherId);
	
	@Query("SELECT t FROM Teacher t JOIN t.programs p WHERE p.programid = :programId")
    List<Teacher> findTeachersByProgramId(@Param("programId") int programId);

	@Query("SELECT p FROM Teacher p WHERE p.department = :department AND p.faculty = :faculty")
	List<Teacher> findTeacherByDepartmentAndFaculty(Department department, Faculty faculty);

	List<Teacher> findAllByDepartment(Department teacherDepartment);

	@Query("SELECT t FROM Teacher t JOIN t.semesters s WHERE s = :semester AND :program MEMBER OF t.programs")
	List<Teacher> findTeachersByProgramAndSemester(@Param("program") Program program, @Param("semester") Semester semester);

 }