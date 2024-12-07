package com.ams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department createDepartment(String departmentName, Faculty faculty, String details, boolean active,
			String createdBy, String modifiedBy) {
		Department department = new Department();
		department.setDepartment(departmentName);
		department.setFaculty(faculty);
		department.setDetails(details);
		department.setActive(active);
		department.setCreatedon(new Date());
		department.setCreatedBy(createdBy);
		department.setUpdatedon(new Date());
		department.setModifiedBy(modifiedBy);
		return departmentRepository.save(department);
	}

	public Department updateDepartment(int departmentId, String departmentName, Faculty faculty, String details,
			boolean active, String modifiedBy) {
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Department Id:" + departmentId));

		department.setDepartment(departmentName);
		department.setFaculty(faculty);
		department.setDetails(details);
		department.setActive(active);
		department.setUpdatedon(new Date());
		department.setModifiedBy(modifiedBy);

		return departmentRepository.save(department);
	}

	public void deleteDepartment(int departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	public Department getDepartmentById(int departmentId) {
		return departmentRepository.findByDepartmentid(departmentId);
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public List<Department> findByFacultyId(int facultyId) {
		return departmentRepository.findByFaculty_FacultyidAndActiveTrue(facultyId);
	}

	public Department findById(int departmentId) {
		return departmentRepository.findByDepartmentid(departmentId);
	}

	public Department findByTeacherId(int teacherId) {
		return departmentRepository.findDepartmentByTeacherId(teacherId);
	}

}