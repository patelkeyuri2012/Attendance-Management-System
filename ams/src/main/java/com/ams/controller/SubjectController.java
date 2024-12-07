package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Subject;
import com.ams.service.DepartmentService;
import com.ams.service.FacultyService;
import com.ams.service.ProgramService;
import com.ams.service.SemesterService;
import com.ams.service.SubjectService;

@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private SemesterService semesterService;

	@PostMapping("/addSubjects")
	public String addSubject(@RequestParam("subject") String subjectName, @RequestParam("subjectcode") String subjectCode, @RequestParam("details") String details,
			@RequestParam("facultyid") Faculty faculty, @RequestParam("departmentid") Department department,
			@RequestParam("programid") Program program, @RequestParam("semesterid") Semester semester,
			@RequestParam(value = "elective", defaultValue = "false") boolean elective,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {
		if (createdBy == null) {
			createdBy = "defaultUser";
		}
		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}
		subjectService.createSubject(subjectCode, subjectName, faculty, department, program, semester, details, elective,  active, createdBy, modifiedBy);
		return "redirect:/admin/subject.html";
	}

	@PostMapping("/updateSubjects")
	public String updateSubjects(@RequestParam("subjectid") Integer subjectId,
			@RequestParam("department") Integer departmentId, @RequestParam("faculty") Integer facultyId,
			@RequestParam("program") Integer programId, @RequestParam("semester") Integer semesterId,
			@RequestParam("subject") String subjectName, @RequestParam("subjectcode") String subjectCode, @RequestParam("details") String details,
			@RequestParam(value = "elective", defaultValue = "false") boolean elective,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (subjectId == null) {
			throw new IllegalArgumentException("subject ID cannot be null");
		}

		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		Faculty faculty = facultyService.getFacultyById(facultyId);
		Department department = departmentService.getDepartmentById(departmentId);
		Program program = programService.getProgramById(programId);
		Semester semester = semesterService.getSemesterById(semesterId);

		subjectService.updateSubject(subjectId, subjectCode, subjectName, faculty, department, program, semester, details, elective, active, modifiedBy);
		return "redirect:/admin/subject.html";
	}

	@PostMapping("/deleteSubjects")
	public String deleteSubject(@RequestParam("subjectId") int subjectId) {
		subjectService.deleteSubject(subjectId);
		return "redirect:/admin/subject.html";
	}

	@PostMapping("/addSubject")
	public String addSubjects(
	    @RequestParam("subject") String subjectName, 
	    @RequestParam("subjectcode") String subjectCode, 
	    @RequestParam("details") String details,
	    @RequestParam("facultyid") Integer facultyId, 
	    @RequestParam("departmentid") Integer departmentId,
	    @RequestParam("programid") Program program, 
	    @RequestParam("semesterid") Semester semester,
	    @RequestParam(value = "elective", defaultValue = "false") boolean elective,
	    @RequestParam(value = "active", defaultValue = "false") boolean active,
	    @RequestParam(value = "createdBy", required = false) String createdBy,
	    @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {
		
		System.out.println(subjectName +" "+ subjectCode +" "+ details +" "+ facultyId +" "+ departmentId +" "+ program +" "+ semester +" "+ elective);

	    if (createdBy == null) {
	        createdBy = "defaultUser";
	    }
	    if (modifiedBy == null) {
	        modifiedBy = "defaultUser";
	    }

	    Faculty faculty = facultyService.getFacultyById(facultyId);
	    Department department = departmentService.getDepartmentById(departmentId);
	    
	    subjectService.createSubject(subjectCode, subjectName, faculty, department, program, semester, details, elective, active, createdBy, modifiedBy);

	    return "redirect:/HOD/subject.html";
	}



	@PostMapping("/updateSubject")
	public String updateSubject(@RequestParam("subjectid") Integer subjectId,
			@RequestParam("program") Integer programId, @RequestParam("semester") Integer semesterId,
			@RequestParam("subject") String subjectName, @RequestParam("subjectcode") String subjectCode, @RequestParam("details") String details,
			@RequestParam(value = "elective", defaultValue = "false") boolean elective,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (subjectId == null) {
			throw new IllegalArgumentException("subject ID cannot be null");
		}

		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		Program program = programService.getProgramById(programId);
		Semester semester = semesterService.getSemesterById(semesterId);

		subjectService.updateSubject(subjectId, subjectCode, subjectName, program, semester, details, elective, active, modifiedBy);
		return "redirect:/HOD/subject.html";
	}

	@PostMapping("/deleteSubject")
	public String deleteSubjects(@RequestParam("subjectId") int subjectId) {
		subjectService.deleteSubject(subjectId);
		return "redirect:/HOD/subject.html";
	}

	@GetMapping("/getSubject/{id}")
	public String getSubjectById(@PathVariable("id") int SubjectId, Model model) {
		Subject subject = subjectService.getSubjectById(SubjectId);
		model.addAttribute("subject", subject);
		return "admin/subject.html";
	}

	@GetMapping("/getAllSubjects")
	public List<Subject> getAllSubjects() {
		return subjectService.findAll();
	}
}