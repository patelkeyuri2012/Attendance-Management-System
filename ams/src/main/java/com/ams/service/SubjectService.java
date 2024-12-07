package com.ams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Subject;
import com.ams.repository.DepartmentRepository;
import com.ams.repository.ProgramRepository;
import com.ams.repository.SemesterRepository;
import com.ams.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@Autowired
	public DepartmentRepository departmentRepository;

	public Subject createSubject(String subjectCode, String subjectName, Faculty faculty, Department department, Program program,
			Semester semester, String details, boolean elective, boolean active, String createdBy, String modifiedBy) {
		Subject subject = new Subject();
		subject.setSubjectcode(subjectCode);
		subject.setSubject(subjectName);
		subject.setFaculty(faculty);
		subject.setDepartment(department);
		subject.setProgram(program);
		subject.setSemester(semester);
		subject.setDetails(details);
		subject.setElective(elective);
		subject.setActive(active);
		subject.setCreatedon(new Date());
		subject.setCreatedBy(createdBy);
		subject.setUpdatedon(new Date());
		subject.setModifiedBy(modifiedBy);
		return subjectRepository.save(subject);
	}

	public Subject updateSubject(int subjectId, String subjectCode, String subjectName, Faculty faculty, Department department,
			Program program, Semester semester, String details, boolean elective,  boolean active, String modifiedBy) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Subject Id:" + subjectId));

		subject.setSubjectcode(subjectCode);
		subject.setSubject(subjectName);
		subject.setFaculty(faculty);
		subject.setDepartment(department);
		subject.setProgram(program);
		subject.setSemester(semester);
		subject.setDetails(details);
		subject.setElective(elective);
		subject.setActive(active);
		subject.setUpdatedon(new Date());
		subject.setModifiedBy(modifiedBy);

		return subjectRepository.save(subject);
	}

	public void deleteSubject(int subjectId) {
		subjectRepository.deleteById(subjectId);
	}

	public Subject getSubjectById(int subjectId) {
		return subjectRepository.findBySubjectid(subjectId);
	}
	
	public List<Subject> findByProgramAndSemester(int programId, int semesterId) {
        return subjectRepository.findByProgram_ProgramidAndSemester_Semesterid(programId, semesterId);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    
    public List<Subject> getSubjectsByIds(List<Integer> subjectIds) {
        return subjectRepository.findAllById(subjectIds);
    }

	public List<Subject> findProgramByDepartment(Department teacherDepartment) {
		return subjectRepository.findAllByDepartment(teacherDepartment);
	}

	public Subject updateSubject(int subjectId, String subjectCode, String subjectName,
			Program program, Semester semester, String details, boolean elective,  boolean active, String modifiedBy) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Subject Id:" + subjectId));

		subject.setSubjectcode(subjectCode);
		subject.setSubject(subjectName);
		subject.setProgram(program);
		subject.setSemester(semester);
		subject.setDetails(details);
		subject.setElective(elective);
		subject.setActive(active);
		subject.setUpdatedon(new Date());
		subject.setModifiedBy(modifiedBy);

		return subjectRepository.save(subject);
	}

	public List<Subject> getSubjectsByProgramAndSemester(int programId, int semesterId) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new RuntimeException("Program not found"));
        Semester semester = semesterRepository.findById(semesterId).orElseThrow(() -> new RuntimeException("Semester not found"));
        
        return subjectRepository.findByProgramAndSemester(program, semester);
    }

	public List<Subject> getSubjectsByProgramAndSemester(List<Semester> semesters, List<Program> programs) {
		Program program = (Program) programRepository.findAll();
        Semester semester = (Semester) semesterRepository.findAll();
        
        return subjectRepository.findByProgramAndSemester(program, semester);
	}
	
	public List<Subject> findSubjectsByProgramAndSemester(Program program, Semester semester) {
        return subjectRepository.findByProgramAndSemester(program, semester);
    }

	  public List<Subject> findProgramByDepartment(int departmentId) {
	        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
	        return subjectRepository.findAllByDepartment(department);
	    }

	public List<Subject> findSubjectsByProgramAndBySemester(int programId, int semesterId) {
		return subjectRepository.findByProgramIdAndBySemesterId(programId, semesterId);
	}

}