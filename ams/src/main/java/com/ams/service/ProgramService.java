package com.ams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Teacher;
import com.ams.repository.ProgramRepository;
import com.ams.repository.TeacherRepository;

@Service
public class ProgramService {

	@Autowired
	private ProgramRepository programRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	public Program createProgram(String programName, Faculty faculty, Department department, Semester semester,
			String details, boolean active, String createdBy, String modifiedBy) {
		Program program = new Program();
		program.setProgram(programName);
		program.setFaculty(faculty);
		program.setDepartment(department);
		program.setSemester(semester);
		program.setDetails(details);
		program.setActive(active);
		program.setCreatedon(new Date());
		program.setCreatedBy(createdBy);
		program.setUpdatedon(new Date());
		program.setModifiedBy(modifiedBy);
		return programRepository.save(program);
	}

	public Program updateProgram(int programId, String programName, Faculty faculty, Department department,
			Semester semester, String details, boolean active, String modifiedBy) {
		Program program = programRepository.findById(programId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Program Id:" + programId));

		program.setProgram(programName);
		program.setFaculty(faculty);
		program.setSemester(semester);
		program.setDepartment(department);
		program.setDetails(details);
		program.setActive(active);
		program.setUpdatedon(new Date());
		program.setModifiedBy(modifiedBy);

		return programRepository.save(program);
	}

	public void deleteProgram(int programId) {
		programRepository.deleteById(programId);
	}

	public Program getProgramById(int programId) {
		return programRepository.findByProgramid(programId);
	}

	public List<Program> findAll() {
		return programRepository.findAll();
	}

	public List<Program> findByDepartmentId(int departmentId) {
		return programRepository.findByDepartment_DepartmentidAndActiveTrue(departmentId);
	}

	public List<Program> getProgramByIds(List<Integer> programIds) {
		return programRepository.findAllById(programIds);
	}

	public List<Program> findByDepartment(Department userDepartment) {
		return programRepository.findByDepartment(userDepartment);
	}

	public List<Program> getProgramsByTeacherId(int teacherId) {
		Teacher teacher = teacherRepository.findByTeacherid(teacherId);
		return teacher.getPrograms();
	}

	public List<Program> findProgramsByDepartmentAndFaculty(Department department, Faculty faculty) {
		return programRepository.findProgramsByDepartmentAndFaculty(department, faculty);
	}

	public int getMaxSemesterByProgram(int programid) {
		Integer maxSemesterId = programRepository.findMaxSemesterByProgramId(programid);
		return (maxSemesterId != null) ? maxSemesterId : 0;
	}

	public Program findByTeacherId(int teacherid) {
		 return programRepository.findProgramByTeacherId(teacherid);
	}

	public Program findById(int programId) {
		return programRepository.findByProgramid(programId);
	}
}