package com.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Department findByDepartmentid(int departmentId);

	List<Department> findByFaculty_FacultyidAndActiveTrue(int facultyId);

	@Query("SELECT t.department FROM Teacher t WHERE t.teacherid = :teacherId")
	Department findDepartmentByTeacherId(@Param("teacherId") int teacherId);

	
}