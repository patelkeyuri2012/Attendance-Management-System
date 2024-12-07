package com.ams.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Student;
import com.ams.service.MailService;
import com.ams.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private MailService mailService;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int PASSWORD_LENGTH = 10;

	@PostMapping("/addStudent")
	public String addStudent(@RequestParam("entrollmentno") String entrollmentno,
			@RequestParam("fname") String firstName, @RequestParam("mname") String middleName,
			@RequestParam("lname") String lastName, @RequestParam("email") String email,
			@RequestParam("contactnumber") String contactnumber,
			@RequestParam("faculty") int facultyId, @RequestParam("department") int departmentId,
			@RequestParam("program") int programId, @RequestParam("semester") int semesterId,
			@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		try {
			String randomPassword = generateRandomPassword();

			studentService.saveStudent(entrollmentno, firstName, middleName, lastName, email, contactnumber,
					facultyId, departmentId, programId, semesterId, active,
					createdBy, modifiedBy, randomPassword);

			String gvpemail = entrollmentno + ".gvp@gujaratvidyapith.org";
			
			String message = "Dear " + firstName + ",\n\n"
					+ "Your account has been created. Your login credentials are:\n" + "Email: " + gvpemail + "\n"
					+ "Password: " + randomPassword + "\n\n" + "Best regards,\nVAMS";
			System.out.println(message);
			boolean isEmailSent = mailService.sendEmail(email, message, "Your Account Details");
			if (!isEmailSent) {
				System.out.println("Student added, but failed to send email.");
			}

			return "redirect:/admin/student.html";

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/student.html";
		}
	}

	@PostMapping("/updateStudent")
	public String updateStudent(@RequestParam("studentid") Integer studentId, @RequestParam("fname") String firstName,
			@RequestParam("mname") String middleName, @RequestParam("lname") String lastName,
			@RequestParam("email") String email, @RequestParam("contactnumber") String contactnumber,
			@RequestParam("faculty") int facultyId,
			@RequestParam("department") int departmentId, @RequestParam("program") int programId,
			@RequestParam("semester") int semesterId,@RequestParam(value = "active", defaultValue = "false") boolean active,
			@RequestParam(value = "modifiedBy", required = false, defaultValue = "defaultUser") String modifiedBy) {

		if (studentId == null) {
			throw new IllegalArgumentException("Student ID cannot be null");
		}

		studentService.updateStudent(studentId, firstName, middleName, lastName, email, contactnumber,
				facultyId, departmentId, programId,semesterId, active,
				modifiedBy);

		return "redirect:/admin/student.html";
	}

	@PostMapping("/deleteStudents")
    public String deleteStudent(@RequestParam("studentid") int studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/admin/student.html";
    }
	
	@PostMapping("/addStudentSubject")
	public String addStudentSubject(
	        @RequestParam(value = "studentIds", required = false) List<Integer> studentIds,
	        @RequestParam("subjectId") Integer subjectId) {

	    if (studentIds == null) {
	        System.out.println("No student IDs received.");
	        return "redirect:/HOD/subject.html";
	    }

	    studentService.addStudentSubject(studentIds, subjectId);
	    return "redirect:/HOD/subject.html";
	}

	  
	@GetMapping("/getStudent/{id}")
	public String getStudentById(@PathVariable("id") int studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "admin/student.html";
	}

	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
		return studentService.findAll();
	}
	
	@GetMapping("/getAllStudent")
	public List<Student> getAllStudents(HttpServletRequest request) {
	    Integer hodDepartmentId = (Integer) request.getSession().getAttribute("hodDepartmentId");
	    return studentService.findStudentsByDepartmentId(hodDepartmentId);
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