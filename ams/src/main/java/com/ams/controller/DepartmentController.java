package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.service.DepartmentService;
import com.ams.service.FacultyService;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private FacultyService facultyService;

    @PostMapping("/addDepartments")
    public String addDepartment(@RequestParam("department") String departmentName,
        @RequestParam("details") String details,
        @RequestParam("facultyid") Faculty faculty,
        @RequestParam(value = "active", defaultValue = "false") boolean active,
        @RequestParam(value = "createdBy", required = false) String createdBy,
        @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {
        if (createdBy == null) {
            createdBy = "defaultUser";
        }
        if (modifiedBy == null) {
            modifiedBy = "defaultUser";
        }
        departmentService.createDepartment(departmentName, faculty, details, active, createdBy, modifiedBy);
        return "redirect:/admin/department.html";
    }


    @PostMapping("/updateDepartments")
    public String updateDepartment(@RequestParam("departmentid") Integer departmentId,
                                   @RequestParam("faculty") Integer facultyId,
                                   @RequestParam("department") String departmentName,
                                   @RequestParam("details") String details,
                                   @RequestParam(value = "active", defaultValue = "false") boolean active,
                                   @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

        if (departmentId == null) {
            throw new IllegalArgumentException("Department ID cannot be null");
        }

        if (modifiedBy == null) {
            modifiedBy = "defaultUser";
        }

        Faculty faculty = facultyService.getFacultyById(facultyId);

        departmentService.updateDepartment(departmentId, departmentName, faculty, details, active, modifiedBy);
        return "redirect:/admin/department.html";
    }


    @PostMapping("/deleteDepartments")
    public String deleteDepartment(@RequestParam("departmentId") int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return "redirect:/admin/department.html";
    }

    @GetMapping("/getDepartment/{id}")
    public String getDepartmentById(@PathVariable("id") int DepartmentId, Model model) {
		Department department = departmentService.getDepartmentById(DepartmentId);
        model.addAttribute("department", department);
        return "admin/department.html";
    }

    @GetMapping("/getAllDepartments")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }
}