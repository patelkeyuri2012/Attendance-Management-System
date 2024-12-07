package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.entity.Schedule;
import com.ams.entity.Addmission;
import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.TeacherLeave;
import com.ams.entity.Program;
import com.ams.entity.Registration;
import com.ams.entity.Role;
import com.ams.entity.Semester;
import com.ams.entity.Student;
import com.ams.entity.Subject;
import com.ams.entity.Teacher;
import com.ams.entity.User;
import com.ams.entity.UserRole;
import com.ams.service.ScheduleService;
import com.ams.service.AddmissionService;
import com.ams.service.DepartmentService;
import com.ams.service.FacultyService;
import com.ams.service.TeacherLeaveService;
import com.ams.service.ProgramService;
import com.ams.service.RegistrationService;
import com.ams.service.RoleService;
import com.ams.service.SemesterService;
import com.ams.service.StudentService;
import com.ams.service.SubjectService;
import com.ams.service.TeacherService;
import com.ams.service.UserRoleService;
import com.ams.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private AddmissionService addmissionService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private TeacherLeaveService teacherLeaveService;

	@GetMapping("/admin/faculty.html")
	public String getAdminFaculty(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		return checkSessionAndRole(session, redirectAttributes, "admin/faculty.html", "ADMIN", model);
	}

	@GetMapping("/admin/department.html")
	public String getAdminDepartment(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);

		return checkSessionAndRole(session, redirectAttributes, "admin/department.html", "ADMIN", model);
	}

	@GetMapping("/admin/program.html")
	public String getAdminProgram(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);

		List<Program> programs = programService.findAll();
		model.addAttribute("programs", programs);

		return checkSessionAndRole(session, redirectAttributes, "admin/program.html", "ADMIN", model);
	}

	@GetMapping("/admin/subject.html")
	public String getAdminSubject(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);

		List<Program> programs = programService.findAll();
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findAll();
		model.addAttribute("subjects", subjects);

		return checkSessionAndRole(session, redirectAttributes, "admin/subject.html", "ADMIN", model);
	}

	@GetMapping("/admin/teacher.html")
	public String getAdminTeacher(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);

		List<Program> programs = programService.findAll();
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findAll();
		model.addAttribute("subjects", subjects);

		List<Teacher> teachers = teacherService.findAll();
		model.addAttribute("teachers", teachers);

		List<Registration> registrations = registrationService.findAll();
		model.addAttribute("registrations", registrations);

		return checkSessionAndRole(session, redirectAttributes, "admin/teacher.html", "ADMIN", model);
	}

	@GetMapping("/admin/student.html")
	public String getAdminStudent(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<Faculty> faculties = facultyService.findAll();
		model.addAttribute("faculties", faculties);

		List<Department> departments = departmentService.findAll();
		model.addAttribute("departments", departments);

		List<Program> programs = programService.findAll();
		model.addAttribute("programs", programs);

		List<Semester> semesters = semesterService.findAll();
		model.addAttribute("semesters", semesters);

		List<Subject> subjects = subjectService.findAll();
		model.addAttribute("subjects", subjects);

		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);

		List<Addmission> addmissions = addmissionService.findAll();
		model.addAttribute("addmissions", addmissions);

		return checkSessionAndRole(session, redirectAttributes, "admin/student.html", "ADMIN", model);
	}

	@GetMapping("/admin/role.html")
	public String getAdminRole(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);

		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);

		List<UserRole> userRoles = userRoleService.findAll();
		model.addAttribute("userRoles", userRoles);

		return checkSessionAndRole(session, redirectAttributes, "admin/role.html", "ADMIN", model);
	}

	@GetMapping("/admin/result.html")
	public String getAdminResult(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		return checkSessionAndRole(session, redirectAttributes, "admin/result.html", "ADMIN", model);
	}

	@GetMapping("/admin/leave.html")
	public String getAdminLeave(HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		List<TeacherLeave> leaves = teacherLeaveService.findAll();
		model.addAttribute("leaves", leaves);

		return checkSessionAndRole(session, redirectAttributes, "admin/leave.html", "ADMIN", model);
	}

	@GetMapping("/admin/academicCalendar.html")
	public String getAdminAcademicCalendar1(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		List<Schedule> schedules = scheduleService.findAll();
		model.addAttribute("schedules", schedules);
		return checkSessionAndRole(session, redirectAttributes, "admin/academicCalendar.html", "ADMIN", model);
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
