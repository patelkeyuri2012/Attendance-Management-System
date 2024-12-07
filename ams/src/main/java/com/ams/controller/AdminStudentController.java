package com.ams.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.entity.LeaveType;
import com.ams.entity.Student;
import com.ams.entity.StudentLeave;
import com.ams.entity.User;
import com.ams.service.AttendanceService;
import com.ams.service.LeaveTypeService;
import com.ams.service.StudentLeaveService;
import com.ams.service.StudentService;
import com.ams.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminStudentController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LeaveTypeService leaveTypeService;
	
	@Autowired
	private StudentLeaveService studentLeaveService;
	
	@Autowired
	private  AttendanceService attendanceService;

	@GetMapping("/student/attendance.html")
	public String getStudentAttendance(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
	    User user = (User) session.getAttribute("user");

	    if (user == null) {
	        return "redirect:/login"; 
	    }

	    String email = user.getEmail();
	    Student student = studentService.getStudentByEmail(email);
	    
	    if (student == null) {
	        System.out.println("Student not found for email: " + email);
	        return "redirect:/student/attendance.html"; 
	    }

	    List<Map<String, Object>> attendanceList = attendanceService.getAttendanceData(student.getStudentid());
	 
	    model.addAttribute("attendanceList", attendanceList);
	    return checkSessionAndRole(session, redirectAttributes, "/student/attendance.html", "STUDENT", model); 
	}


	@GetMapping("/student/schedules.html")
	public String getStudentSchedules(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "student/schedules.html", "STUDENT", model);
	}

	@GetMapping("/student/exam.html")
	public String adminSomePage(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "/student/exam.html", "STUDENT", model);
	}

	@GetMapping("/student/leave.html")
	public String getStudentLeave(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Student student = studentService.getStudentByEmail(email);

		if (student == null) {
			System.out.println("Student not found for email: " + email);
			return "redirect:/student/leave.html";
		}

		List<LeaveType> leaveTypes = leaveTypeService.findAll();
		model.addAttribute("leaveTypes", leaveTypes);

		List<StudentLeave> leaves = studentLeaveService.findByStudentId(student);
		model.addAttribute("leaves", leaves);
		return checkSessionAndRole(session, redirectAttributes, "student/leave.html", "STUDENT", model);
	}


	private String checkSessionAndRole(HttpSession session, RedirectAttributes redirectAttributes, String targetPage,
			String requiredRole, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			redirectAttributes.addFlashAttribute("error", "You must be logged in to access this page");
			return "redirect:/";
		} else if (!user.getRole().getName().equals(requiredRole)) {
			redirectAttributes.addFlashAttribute("error", "You do not have permission to access this page");
			return "redirect:/";
		} else {
			Object userDetails = userService.getUserDetailsByRole(user);
			if (userDetails == null) {
				System.out.println("User details are null");
			} else {
				System.out.println("User details: " + userDetails);
			}
			model.addAttribute("userDetails", userDetails);

			return targetPage;
		}
	}

}
