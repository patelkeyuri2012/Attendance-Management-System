package com.ams.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.TeacherLeave;
import com.ams.entity.LeaveType;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.StudentLeave;
import com.ams.entity.Subject;
import com.ams.entity.TakeAttendance;
import com.ams.entity.Teacher;
import com.ams.entity.User;
import com.ams.service.TeacherLeaveService;
import com.ams.service.LeaveTypeService;
import com.ams.service.ProgramService;
import com.ams.service.SemesterService;
import com.ams.service.StudentLeaveService;
import com.ams.service.StudentService;
import com.ams.service.SubjectService;
import com.ams.service.TakeAttendanceService;
import com.ams.service.TeacherService;
import com.ams.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminHODController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private LeaveTypeService leaveTypeService;

	@Autowired
	private TeacherLeaveService teacherLeaveService;

	@Autowired
	private StudentLeaveService studentLeaveService;

	@Autowired
	private TakeAttendanceService takeAttendanceService;

	@GetMapping("/HOD/student.html")
	public String getHODStudent(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/student.html";
		}

		List<Program> programs = teacherService.getProgramsByTeacherDepartment(teacher.getTeacherid());
		Map<Program, List<Map<String, Object>>> programSemesterStudentMap = new HashMap<>();

		for (Program program : programs) {
			List<Semester> semesters = semesterService.findAll();
			List<Map<String, Object>> semesterDetailsList = new ArrayList<>();

			int maxSemester = programService.getMaxSemesterByProgram(program.getProgramid());

			for (Semester semester : semesters) {
				if (semester.getSemesterid() <= maxSemester) {
					int studentCount = studentService.getStudentCountByProgramAndSemester(program.getProgramid(),
							semester.getSemesterid());
					int activeCount = studentService.getActiveStudentCountByProgramAndSemester(program.getProgramid(),
							semester.getSemesterid());
					int deactiveCount = studentService.getDeactiveStudentCountByProgramAndSemester(
							program.getProgramid(), semester.getSemesterid());

					Map<String, Object> semesterDetails = new HashMap<>();
					semesterDetails.put("semester", semester);
					semesterDetails.put("studentCount", studentCount);
					semesterDetails.put("activeCount", activeCount);
					semesterDetails.put("deactiveCount", deactiveCount);

					semesterDetailsList.add(semesterDetails);
				}
			}

			programSemesterStudentMap.put(program, semesterDetailsList);
		}

		Map<String, List<Map<String, Object>>> simplifiedMap = new HashMap<>();
		for (Map.Entry<Program, List<Map<String, Object>>> entry : programSemesterStudentMap.entrySet()) {
			String programName = entry.getKey().getProgram();
			simplifiedMap.put(programName, entry.getValue());
		}

		model.addAttribute("simplifiedProgramSemesterStudentMap", simplifiedMap);
		model.addAttribute("programSemesterStudentMap", programSemesterStudentMap);

		return checkSessionAndRole(session, redirectAttributes, "HOD/student.html", "HOD", model);
	}

	@GetMapping("/HOD/schedules.html")
	public String getHODSchedules(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/schedules.html";
		}

		List<Program> programs = teacherService.getProgramsByTeacherDepartment(teacher.getTeacherid());
		Map<Program, List<Map<String, Object>>> programSemesterStudentMap = new HashMap<>();

		for (Program program : programs) {
			List<Semester> semesters = semesterService.findAllByIsActive(true);
			List<Map<String, Object>> semesterDetailsList = new ArrayList<>();

			int maxSemester = programService.getMaxSemesterByProgram(program.getProgramid());

			for (Semester semester : semesters) {
				if (semester.getSemesterid() <= maxSemester) {
					int studentCount = studentService.getStudentCountByProgramAndSemester(program.getProgramid(),
							semester.getSemesterid());
					int activeCount = studentService.getActiveStudentCountByProgramAndSemester(program.getProgramid(),
							semester.getSemesterid());
					int deactiveCount = studentService.getDeactiveStudentCountByProgramAndSemester(
							program.getProgramid(), semester.getSemesterid());

					Map<String, Object> semesterDetails = new HashMap<>();
					semesterDetails.put("semester", semester);
					semesterDetails.put("studentCount", studentCount);
					semesterDetails.put("activeCount", activeCount);
					semesterDetails.put("deactiveCount", deactiveCount);

					semesterDetailsList.add(semesterDetails);
				}
			}

			programSemesterStudentMap.put(program, semesterDetailsList);
		}

		Map<String, List<Map<String, Object>>> simplifiedMap = new HashMap<>();
		for (Map.Entry<Program, List<Map<String, Object>>> entry : programSemesterStudentMap.entrySet()) {
			String programName = entry.getKey().getProgram();
			simplifiedMap.put(programName, entry.getValue());
		}

		Department teacherDepartment = teacher.getDepartment();

		List<Subject> subjects = subjectService.findProgramByDepartment(teacherDepartment);
		model.addAttribute("subjects", subjects);

		List<Teacher> teachers = teacherService.findTeacherByDepartment(teacherDepartment);
		model.addAttribute("teachers", teachers);

		model.addAttribute("simplifiedProgramSemesterStudentMap", simplifiedMap);
		model.addAttribute("programSemesterStudentMap", programSemesterStudentMap);

		return checkSessionAndRole(session, redirectAttributes, "HOD/schedules.html", "HOD", model);
	}

	@GetMapping("/HOD/subject.html")
	public String getHODSubject(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/subject.html";
		}

		Department teacherDepartment = teacher.getDepartment();
		Faculty teacherFaculty = teacher.getFaculty();

		List<Program> programs = programService.findProgramsByDepartmentAndFaculty(teacherDepartment, teacherFaculty);
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findProgramByDepartment(teacherDepartment);
		model.addAttribute("subjects", subjects);

		return checkSessionAndRole(session, redirectAttributes, "HOD/subject.html", "HOD", model);
	}

	@GetMapping("/HOD/takeAttendance.html")
	public String getHODTakeAttendance(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/takeAttendance.html";
		}

		Department teacherDepartment = teacher.getDepartment();
		Faculty teacherFaculty = teacher.getFaculty();

		List<Program> programs = programService.findProgramsByDepartmentAndFaculty(teacherDepartment, teacherFaculty);
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findProgramByDepartment(teacherDepartment);
		model.addAttribute("subjects", subjects);

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = today.format(formatter);
		model.addAttribute("today", formattedDate.toString());

		java.sql.Date todays = new java.sql.Date(System.currentTimeMillis());

		List<TakeAttendance> attendanceList = takeAttendanceService.getUpcomingAndPendingAttendance(teacher, todays);

		model.addAttribute("attendanceList", attendanceList);

		return checkSessionAndRole(session, redirectAttributes, "HOD/takeAttendance.html", "HOD", model);
	}

	@GetMapping("/HOD/viewAttendance.html")
	public String getHODViewAttendance(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/HOD/viewAttendance.html";
		}

		Department teacherDepartment = teacher.getDepartment();
		Faculty teacherFaculty = teacher.getFaculty();

		List<Program> programs = programService.findProgramsByDepartmentAndFaculty(teacherDepartment, teacherFaculty);
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findProgramByDepartment(teacherDepartment);
		model.addAttribute("subjects", subjects);

		return checkSessionAndRole(session, redirectAttributes, "HOD/viewAttendance.html", "HOD", model);
	}

	@GetMapping("/HOD/exam.html")
	public String getHODExam(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "HOD/exam.html", "HOD", model);
	}

	@GetMapping("/HOD/result.html")
	public String getHODResult(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "HOD/result.html", "HOD", model);
	}

	@GetMapping("/HOD/takeLeave.html")
	public String getHODTakeLeave(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return "redirect:/takeLeave.html";
		}

		List<LeaveType> leaveTypes = leaveTypeService.findAll();
		model.addAttribute("leaveTypes", leaveTypes);

		List<TeacherLeave> leaves = teacherLeaveService.findByTeacherId(teacher);
		model.addAttribute("leaves", leaves);

		return checkSessionAndRole(session, redirectAttributes, "HOD/takeLeave.html", "HOD", model);
	}

	@GetMapping("/HOD/viewLeave.html")
	public String getHODViewLeave(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<StudentLeave> leaves = studentLeaveService.findAll();
		model.addAttribute("leaves", leaves);

		return checkSessionAndRole(session, redirectAttributes, "HOD/viewLeave.html", "HOD", model);
	}

	@GetMapping("/HOD/profile.html")
	public String getHODProfile(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "HOD/profile.html", "HOD", model);
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
