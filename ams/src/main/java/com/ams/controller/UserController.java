package com.ams.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.entity.Schedule;
import com.ams.entity.Teacher;
import com.ams.entity.TeacherLeave;
import com.ams.entity.Timetable;
import com.ams.entity.User;
import com.ams.service.AuthService;
import com.ams.service.OtpService;
import com.ams.service.ScheduleService;
import com.ams.service.TakeAttendanceService;
import com.ams.service.TeacherLeaveService;
import com.ams.service.TeacherService;
import com.ams.service.TimetableService;
import com.ams.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserService teacherService;

	@Autowired
	private AuthService authService;

	@Autowired
	private OtpService otpService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private TeacherLeaveService teacherLeaveService;

	@Autowired
	private TeacherService teachersService;
	
	@Autowired
	private UserService studentService;

	@Autowired
	private TimetableService timetableService;
	
	@Autowired
	private TakeAttendanceService takeAttendanceService;

	@GetMapping("/")
	public String login() {
		return "login.html";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login.html";
	}

	@PostMapping("/login")
	public String postLogin(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {
			if (!email.matches("[a-zA-Z0-9._%+-]+@gujaratvidyapith\\.org")) {
				redirectAttributes.addFlashAttribute("error", "Email should be from gujaratvidyapith.org domain");
				return "redirect:/";
			}

			User authenticatedUser = authService.authenticate(email, password);
			if (authenticatedUser != null) {
				if (authenticatedUser.isBlocked()) {
					redirectAttributes.addFlashAttribute("error",
							"Your account has been blocked. Please contact support.");
					return "redirect:/";
				}

				session.setAttribute("user", authenticatedUser);

				switch (authenticatedUser.getRole().getName()) {
				case "ADMIN":
					return "redirect:/admin/index.html";
				case "HOD":
					return "redirect:/HOD/index.html";
				case "TEACHER":
					return "redirect:/teacher/index.html";
				case "STUDENT":
					return "redirect:/student/index.html";
				default:
					return "redirect:/";
				}
			} else {
				redirectAttributes.addFlashAttribute("error", "Invalid email or password");
				return "redirect:/";
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "An error occurred. Please try again.");
			return "redirect:/";
		}
	}

	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login";
	}

	@GetMapping("/admin/index.html")
	public String getAdminIndex(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		List<Schedule> schedules = scheduleService.findAll();

		YearMonth currentMonth = YearMonth.now();

		List<Schedule> currentMonthSchedules = schedules.stream().filter(schedule -> {
			LocalDate scheduleDate = LocalDate.of(schedule.getYear(), schedule.getMonth(), schedule.getDay());
			return YearMonth.from(scheduleDate).equals(currentMonth);
		}).collect(Collectors.toList());
		model.addAttribute("schedules", currentMonthSchedules);

		List<TeacherLeave> leaves = teacherLeaveService.findPendingLeaves();
		model.addAttribute("leaves", leaves);

		return checkSessionAndRole(session, redirectAttributes, "admin/index.html", "ADMIN", model);
	}

	@GetMapping("/HOD/index.html")
	public String getHODIndex(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teachersService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/index.html";
		}

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = today.format(formatter);
		model.addAttribute("today", formattedDate.toString());
		
		List<Timetable> timetables = timetableService.findByTeacherIdAndDay(teacher, today.getDayOfWeek().name());
		model.addAttribute("timetables", timetables);
		
		Map<String, Integer> attendanceCounts = takeAttendanceService.getAttendanceCounts(teacher);
		model.addAttribute("attendanceCounts", attendanceCounts);

		List<Schedule> schedules = scheduleService.findAll();
		model.addAttribute("schedules", schedules);

		return checkSessionAndRole(session, redirectAttributes, "HOD/index.html", "HOD", model);
	}

	@GetMapping("/teacher/index.html")
	public String getTeacherIndex(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teachersService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/teacher/index.html";
		}

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = today.format(formatter);
		model.addAttribute("today", formattedDate.toString());
		
		List<Timetable> timetables = timetableService.findByTeacherIdAndDay(teacher, today.getDayOfWeek().name());
		model.addAttribute("timetables", timetables);
		
		Map<String, Integer> attendanceCounts = takeAttendanceService.getAttendanceCounts(teacher);
		model.addAttribute("attendanceCounts", attendanceCounts);

		List<Schedule> schedules = scheduleService.findAll();
		model.addAttribute("schedules", schedules);

		return checkSessionAndRole(session, redirectAttributes, "teacher/index.html", "TEACHER", model);
	}
	
	@GetMapping("/student/index.html")
	public String getStudentIndex(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "student/index.html", "STUDENT", model);
	}

	@PostMapping("/updatetHODProfile")
	public String updateHODProfile(@RequestParam("teacherid") int teacherId,
			@RequestParam("profileImageInput") MultipartFile image, @RequestParam("fname") String firstName,
			@RequestParam("mname") String middleName, @RequestParam("lname") String lastName,
			@RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
			@RequestParam("contactNo") String contactNo, @RequestParam("email") String email,
			@RequestParam("address") String address) {

		String imagePath = null;
		if (image != null && !image.isEmpty()) {
	        imagePath = teacherService.saveImage(image);
	    } else {
	        imagePath = teacherService.getExistingTeacherImagePath(teacherId);
	    }

		teacherService.updateProfile(teacherId, imagePath, firstName, middleName, lastName, dob, contactNo, email,
				address);

		return "redirect:/HOD/profile.html";
	}
	
	@PostMapping("/updatetTeacherProfile")
	public String updateTeacherProfile(@RequestParam("teacherid") int teacherId,
			@RequestParam("profileImageInput") MultipartFile image, @RequestParam("fname") String firstName,
			@RequestParam("mname") String middleName, @RequestParam("lname") String lastName,
			@RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
			@RequestParam("contactNo") String contactNo, @RequestParam("email") String email,
			@RequestParam("address") String address) {

		  String imagePath = null;
		    if (image != null && !image.isEmpty()) {
		        imagePath = teacherService.saveImage(image);
		    } else {
		        imagePath = teacherService.getExistingTeacherImagePath(teacherId);
		    }

		teacherService.updateProfile(teacherId, imagePath, firstName, middleName, lastName, dob, contactNo, email,
				address);

		return "redirect:/teacher/profile.html";
	}

	@PostMapping("/updatetStudentProfile")
	public String updateStudentProfile(@RequestParam("studentid") int studentId,
	        @RequestParam(value = "profileImageInput", required = false) MultipartFile image,
	        @RequestParam("fname") String firstName,
	        @RequestParam("mname") String middleName,
	        @RequestParam("lname") String lastName,    
	        @RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
	        @RequestParam("contactNumber") String contactNo,
	        @RequestParam("email") String email,
	        @RequestParam("address") String address) {

		
	    String imagePath = null;
	    if (image != null && !image.isEmpty()) {
	        imagePath = studentService.saveImage(image);
	    } else {
	        imagePath = studentService.getExistingStudentImagePath(studentId);
	    }

	    studentService.updateProfiles(studentId, imagePath, firstName, middleName, lastName, dob, contactNo, email, address);

	    return "redirect:/student/index.html";
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

	@GetMapping("/forgotpassword")
	public String getForgotPassword() {
		return "forgotpassword.html";
	}

	@GetMapping("/resetpassword")
	public String getResetpassword() {
		return "resetpassword.html";
	}

	@PostMapping("/sendotp")
	public String sendOtp(@RequestParam String email, Model model, RedirectAttributes redirectAttributes) {
		if (!email.matches("[a-zA-Z0-9._%+-]+@gujaratvidyapith\\.org")) {
			redirectAttributes.addFlashAttribute("error", "Email should be from gujaratvidyapith.org domain");
			return "redirect:/forgotpassword";
		}
		boolean otpSent = otpService.sendOtp(email);
		if (otpSent) {
			model.addAttribute("email", email);
			return "sendotp.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "Email is not exist. Please try again");
			return "redirect:/forgotpassword";
		}
	}

	@PostMapping("/verifyotp")
	public String verifyOtp(@RequestParam String email, @RequestParam String first, @RequestParam String second,
			@RequestParam String third, @RequestParam String fourth, @RequestParam String fifth,
			@RequestParam String sixth, Model model, RedirectAttributes redirectAttributes) {
		String otp = first + second + third + fourth + fifth + sixth;

		boolean otpVerified = otpService.verifyOtp(email, otp);

		if (otpVerified) {
			model.addAttribute("email", email);
			return "resetpassword.html";
		} else {
			model.addAttribute("email", email);
			redirectAttributes.addFlashAttribute("error", "Invalid OTP. Please try again");
			return "redirect:/forgotpassword";
		}
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@RequestParam String email, @RequestParam String newPassword,
			@RequestParam String confirmpassword, RedirectAttributes redirectAttributes) {
		if (!newPassword.equals(confirmpassword)) {
			redirectAttributes.addFlashAttribute("error", "Passwords do not match");
			return "redirect:/resetpassword";
		}

		boolean passwordReset = userService.resetPassword(email, newPassword);
		if (passwordReset) {
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("error", "An error occurred. Please try again.");
			return "redirect:/forgotpassword";
		}
	}

	@PostMapping("/validateOldPassword")
	@ResponseBody
	public Map<String, Boolean> validateOldPassword(@RequestParam String email, @RequestParam String oldPassword) {
		User user = userService.findByEmail(email);
		Map<String, Boolean> response = new HashMap<>();

		if (user != null && user.getPassword().equals(oldPassword)) {
			response.put("isValid", true);
		} else {
			response.put("isValid", false);
		}
		return response;
	}

	@PostMapping("/changeHODPassword")
	public String changeHODPassword(@RequestParam String email, @RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String confirmpassword,
			RedirectAttributes redirectAttributes) {
		if (!newPassword.equals(confirmpassword)) {
			redirectAttributes.addFlashAttribute("error", "New passwords do not match");
			return "redirect:/changePassword";
		}

		boolean passwordChanged = userService.changePassword(email, oldPassword, newPassword);

		if (passwordChanged) {
			return "redirect:/HOD/profile.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "Old password is incorrect");
			return "redirect:/HOD/profile.html";
		}
	}
	
	@PostMapping("/changeTeacherPassword")
	public String changeTeacherPassword(@RequestParam String email, @RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String confirmpassword,
			RedirectAttributes redirectAttributes) {
		if (!newPassword.equals(confirmpassword)) {
			redirectAttributes.addFlashAttribute("error", "New passwords do not match");
			return "redirect:/changePassword";
		}

		boolean passwordChanged = userService.changePassword(email, oldPassword, newPassword);

		if (passwordChanged) {
			return "redirect:/teacher/profile.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "Old password is incorrect");
			return "redirect:/teacher/profile.html";
		}
	}
	
	@PostMapping("/changeStudentPassword")
	public String changeStudentPassword(@RequestParam String email, @RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String confirmpassword,
			RedirectAttributes redirectAttributes) {
		if (!newPassword.equals(confirmpassword)) {
			redirectAttributes.addFlashAttribute("error", "New passwords do not match");
			return "redirect:/changePassword";
		}

		boolean passwordChanged = userService.changePassword(email, oldPassword, newPassword);

		if (passwordChanged) {
			return "redirect:/student/index.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "Old password is incorrect");
			return "redirect:/student/index.html";
		}
	}

}