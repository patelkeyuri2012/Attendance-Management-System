package com.ams.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Teacher;
import com.ams.service.MailService;
import com.ams.service.ProgramService;
import com.ams.service.SemesterService;
import com.ams.service.SubjectService;
import com.ams.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private SemesterService semesterService;

	@Autowired
	private SubjectService subjectService;
	
	 @Autowired
	    private MailService mailService;
	 
	
	    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    private static final int PASSWORD_LENGTH = 10;

	    @PostMapping("/addTeacher")
	    public String addTeacher(@RequestParam("registrationid") String registrationId,
	                             @RequestParam("fname") String firstName,
	                             @RequestParam("mname") String middleName,
	                             @RequestParam("lname") String lastName,
	                             @RequestParam("email") String email,
	                             @RequestParam("contactNo") String contactNo,
	                             @RequestParam("qualification") String qualification,
	                             @RequestParam("faculty") int facultyId,
	                             @RequestParam("department") int departmentId,
	                             @RequestParam("program") List<Integer> programIds,
	                             @RequestParam("semester") List<Integer> semesterIds,
	                             @RequestParam("subjects") List<Integer> subjectIds,
	                             @RequestParam(value = "active", defaultValue = "false") boolean active,
	                             @RequestParam(value = "createdBy", required = false) String createdBy,
	                             @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

	        try {
	        	System.out.println("Teacher added, but failed to send email.");
	            String randomPassword = generateRandomPassword();
	            
	            String gvpemail = firstName + ".gvp@gujaratvidyapith.org";

	            teacherService.saveTeacher(registrationId, firstName, middleName, lastName, gvpemail, contactNo, qualification,
	                    facultyId, departmentId, programService.getProgramByIds(programIds),
	                    semesterService.getSemesterByIds(semesterIds), subjectService.getSubjectsByIds(subjectIds),
	                    active, createdBy, modifiedBy, randomPassword);
	            
	            String message = "Dear " + firstName + ",\n\n" +
	                             "Your account has been created. Your login credentials are:\n" +
	                             "Email: " + gvpemail + "\n" +
	                             "Password: " + randomPassword + "\n\n" +
	                             "Best regards,\nVAMS";
	            System.out.println(message);
	            boolean isEmailSent = mailService.sendEmail(email, message, "Your Account Details");
	            if (!isEmailSent) {
	                System.out.println("Teacher added, but failed to send email.");
	            }

	            return "redirect:/admin/teacher.html";

	        } catch (Exception e) {
	            e.printStackTrace();
	            return "redirect:/admin/teacher.html";
	        }
	    }


	@PostMapping("/updateTeacher")
	public String updateTeacher(@RequestParam("teacherid") Integer teacherId,
	        @RequestParam("fname") String firstName, @RequestParam("mname") String middleName,
	        @RequestParam("lname") String lastName, @RequestParam("email") String email,
	        @RequestParam("contactNo") String contactNo, @RequestParam("qualification") String qualification,
	        @RequestParam("faculty") int facultyId, @RequestParam("department") int departmentId,
	        @RequestParam("program") int programId, @RequestParam("semester") int semesterId,
	        @RequestParam("program") List<Integer> programIds, 
			@RequestParam("semester") List<Integer> semesterIds,
			@RequestParam("subjects") List<Integer> subjectIds,
			
	        @RequestParam(value = "active", defaultValue = "false") boolean active,
	        @RequestParam(value = "modifiedBy", required = false, defaultValue = "defaultUser") String modifiedBy) {

	    if (teacherId == null) {
	        throw new IllegalArgumentException("Teacher ID cannot be null");
	    }

	    teacherService.updateTeacher(teacherId, firstName, middleName, lastName, email, contactNo, qualification,
	            facultyId, departmentId, programService.getProgramByIds(programIds) , semesterService.getSemesterByIds(semesterIds), subjectService.getSubjectsByIds(subjectIds), active, modifiedBy);

	    return "redirect:/admin/teacher.html";
	}


	@PostMapping("/deleteTeachers")
	public String deleteTeacher(@RequestParam("teacherId") int teacherId) {
	    teacherService.deleteTeacher(teacherId);
	    return "redirect:/admin/teacher.html";
	}


	@GetMapping("/getTeacher/{id}")
	public String getTeacherById(@PathVariable("id") int teacherId, Model model) {
	    Teacher teacher = teacherService.getTeacherById(teacherId);
	    model.addAttribute("teacher", teacher);
	    return "admin/teacher.html"; 
	}


	@GetMapping("/getAllTeachers")
	public List<Teacher> getAllTeachers() {
		return teacherService.findAll();
	}
	
	private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
