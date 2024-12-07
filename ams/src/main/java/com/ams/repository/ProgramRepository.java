package com.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Program findByProgramid(int programId);

	List<Program> findByDepartment_DepartmentidAndActiveTrue(int departmentId);
	List<Program> findByDepartment(Department department);
	List<Program> findAllByDepartment(Department department);

	@Query("SELECT p FROM Program p WHERE p.department = :department AND p.faculty = :faculty")
    List<Program> findProgramsByDepartmentAndFaculty(@Param("department") Department department, @Param("faculty") Faculty faculty);

	@Query("SELECT MAX(p.semester.semesterid) FROM Program p WHERE p.programid = :programid")
	Integer findMaxSemesterByProgramId(@Param("programid") int programid);

	@Query("SELECT t.programs FROM Teacher t WHERE t.teacherid = :teacherId")
	Program findProgramByTeacherId(@Param("teacherId") int teacherId);
}