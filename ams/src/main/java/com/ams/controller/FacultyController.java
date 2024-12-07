package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Faculty;
import com.ams.service.FacultyService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@PostMapping("/addFaculties")
	public String addFaculty(@RequestParam("faculty") String facultyName, @RequestParam("details") String details,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (createdBy == null) {
			createdBy = "defaultUser";
		}
		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		facultyService.createFaculty(facultyName, details, active, createdBy, modifiedBy);
		return "redirect:/admin/faculty.html";
	}

	@PostMapping("/updateFaculties")
	public String updateFaculty(@RequestParam("facultyid") int facultyId, @RequestParam("faculty") String facultyName,
			@RequestParam("details") String details,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		facultyService.updateFaculty(facultyId, facultyName, details, active, modifiedBy);
		return "redirect:/admin/faculty.html";
	}

	@PostMapping("/deleteFaculties")
	public String deleteFaculty(@RequestParam("facultyId") int facultyId) {
		facultyService.deleteFaculty(facultyId);
		return "redirect:/admin/faculty.html";
	}

	@GetMapping("/getFaculty/{id}")
	public String getFacultyById(@PathVariable("id") int facultyId, Model model) {
		Faculty faculty = facultyService.getFacultyById(facultyId);
		model.addAttribute("faculty", faculty);
		return "admin/faculty.html";
	}

	@GetMapping("/getAllFaculties")
	public List<Faculty> getAllFaculties() {
		return facultyService.findAll();
	}
}
