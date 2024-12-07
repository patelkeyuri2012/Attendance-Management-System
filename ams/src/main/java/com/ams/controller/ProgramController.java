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
import com.ams.service.DepartmentService;
import com.ams.service.FacultyService;
import com.ams.service.ProgramService;
import com.ams.service.SemesterService;

@Controller
public class ProgramController {

	@Autowired
	private ProgramService programService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SemesterService semesterService;

	@PostMapping("/addPrograms")
	public String addProgram(@RequestParam("program") String programName, @RequestParam("details") String details,
			@RequestParam("facultyid") Faculty faculty, @RequestParam("departmentid") Department department, @RequestParam("semesterid") int semesterid, 
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {
		if (createdBy == null) {
			createdBy = "defaultUser";
		}
		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}
		
		Semester semester = semesterService.findById(semesterid); 
		
		programService.createProgram(programName, faculty, department, semester, details, active, createdBy, modifiedBy);
		return "redirect:/admin/program.html";
	}

	@PostMapping("/updatePrograms")
	public String updateProgram(@RequestParam("programid") Integer programId,
			@RequestParam("department") Integer departmentId, @RequestParam("faculty") Integer facultyId,
			@RequestParam("semester") Integer semesterId,
			@RequestParam("program") String programName, @RequestParam("details") String details,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (programId == null) {
			throw new IllegalArgumentException("program ID cannot be null");
		}

		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}
		
		Faculty faculty = facultyService.getFacultyById(facultyId);
		Department department = departmentService.getDepartmentById(departmentId);
		Semester semester = semesterService.getSemesterById(semesterId);

		programService.updateProgram(programId, programName, faculty, department, semester, details, active, modifiedBy);
		return "redirect:/admin/program.html";
	}

	@PostMapping("/deletePrograms")
	public String deleteProgram(@RequestParam("programId") int programId) {
		programService.deleteProgram(programId);
		return "redirect:/admin/program.html";
	}

	@GetMapping("/getProgram/{id}")
	public String getProgramById(@PathVariable("id") int ProgramId, Model model) {
		Program program = programService.getProgramById(ProgramId);
		model.addAttribute("program", program);
		return "admin/program.html";
	}

	@GetMapping("/getAllPrograms")
	public List<Program> getAllPrograms() {
		return programService.findAll();
	}
}