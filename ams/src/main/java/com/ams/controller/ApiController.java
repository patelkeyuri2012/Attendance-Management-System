package com.ams.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ams.entity.Addmission;
import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Schedule;
import com.ams.entity.Semester;
import com.ams.entity.Student;
import com.ams.entity.StudentLeave;
import com.ams.entity.Subject;
import com.ams.entity.TakeAttendance;
import com.ams.entity.Teacher;
import com.ams.entity.TeacherLeave;
import com.ams.entity.Timetable;
import com.ams.entity.User;
import com.ams.repository.AttendanceRepository;
import com.ams.repository.StudentLeaveRepository;
import com.ams.repository.TeacherLeaveRepository;
import com.ams.service.AddmissionService;
import com.ams.service.AttendanceService;
import com.ams.service.AttendanceStatusService;
import com.ams.service.DepartmentService;
import com.ams.service.FacultyService;
import com.ams.service.LeaveStatusService;
import com.ams.service.LeaveTypeService;
import com.ams.service.ProgramService;
import com.ams.service.ScheduleService;
import com.ams.service.SemesterService;
import com.ams.service.StudentService;
import com.ams.service.SubjectService;
import com.ams.service.TakeAttendanceService;
import com.ams.service.TeacherService;
import com.ams.service.TimetableService;
import com.ams.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private SemesterService semesterService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private AddmissionService addmissionService;

	@Autowired
	private LeaveTypeService leaveTypeService;

	@Autowired
	private LeaveStatusService leaveStatusService;

	@Autowired
	private AttendanceStatusService attendanceStatusService;

	@Autowired
	private TimetableService timetableService;

	@Autowired
	private TakeAttendanceService takeAttendanceService;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private TeacherLeaveRepository teacherLeaveRepository;

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private StudentLeaveRepository studentLeaveRepository;

	@PostMapping("/createAdmin")
	public String createAdminUser(@RequestParam("fname") String fname, @RequestParam("mname") String mname,
			@RequestParam("lname") String lname, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("qualification") String qualification,
			@RequestParam("contactno") String contactno, @RequestParam("image") MultipartFile image) {

		try {
			String base64Image = convertToBase64(image);
			userService.createAdminUser(fname, mname, lname, email, password, qualification, contactno, base64Image);
			return "Admin user created successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create admin user";
		}
	}

	private String convertToBase64(MultipartFile image) throws IOException {
		byte[] imageBytes = image.getBytes();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	@PostMapping("/createSemester")
	public String createSemester(@RequestParam("semester") String semester) {

		semesterService.createSemester(semester);
		return "Semester user created successfully!";
	}

	@PostMapping("/createLeaveType")
	public String createLeaveType(@RequestParam("leaveType") String leaveType) {

		leaveTypeService.createLeaveType(leaveType);
		return "LeaveType user created successfully!";
	}

	@PostMapping("/createLeaveStatus")
	public String createLeaveStatus(@RequestParam("leaveStatus") String leaveStatus) {

		leaveStatusService.createLeaveStatus(leaveStatus);
		return "LeaveStatus user created successfully!";
	}

	@PostMapping("/createAttendanceStatus")
	public String createAttendanceStatus(@RequestParam("attendanceStatus") String attendanceStatus) {

		attendanceStatusService.createAttendanceStatus(attendanceStatus);
		return "AttendanceStatus user created successfully!";
	}

	@GetMapping("/departments/{facultyId}")
	public List<Department> getDepartmentsByFaculty(@PathVariable int facultyId) {
		return departmentService.findByFacultyId(facultyId);
	}

	@GetMapping("/programs/{departmentId}")
	public List<Program> getProgramsByDepartment(@PathVariable int departmentId) {
		return programService.findByDepartmentId(departmentId);
	}

	@GetMapping("/programs")
	public List<Program> getProgramsByLoggedUser(HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return Collections.emptyList();
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			System.out.println("Teacher not found for email: " + email);
			return Collections.emptyList();
		}

		return programService.findProgramsByDepartmentAndFaculty(teacher.getDepartment(), teacher.getFaculty());
	}

	@GetMapping("/semesters")
	public List<Semester> getAllSemesters() {
		return semesterService.findAll();
	}

	@GetMapping("/semester")
	public List<Semester> getSemestersByProgram(@RequestParam("programId") int programId) {
		Program program = programService.findById(programId);
		if (program == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found");
		}
		return semesterService.findAllByProgram(programId);
	}

	@GetMapping("/activesemester")
	public List<Semester> getActiveSemestersByProgram(@RequestParam("programId") int programId) {
		Program program = programService.findById(programId);
		if (program == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Program not found");
		}
		return semesterService.findAllByProgram(programId);
	}

	@GetMapping("/subjects")
	public List<Subject> getSubjects(@RequestParam int programId, @RequestParam int semesterId) {
		return subjectService.findByProgramAndSemester(programId, semesterId);
	}

	@GetMapping("/schedules")
	public List<Schedule> getAllSchedules() {
		return scheduleService.getAllSchedules();
	}

	@GetMapping("/getAllFaculties")
	public List<Faculty> getAllFaculties() {
		return facultyService.findAll();
	}

	@GetMapping("/getAllDepartments")
	public List<Department> getAllDepartments() {
		return departmentService.findAll();
	}

	@GetMapping("/getAllPrograms")
	public List<Program> getAllPrograms() {
		return programService.findAll();
	}

	@GetMapping("/getStudents")
	public List<Student> getStudents(@RequestParam int programId, @RequestParam int semesterId,
			@RequestParam int subjectId) {
		return studentService.findStudentsByProgramSemesterAndSubject(programId, semesterId, subjectId);
	}

	@GetMapping("/getStudent")
	public List<Student> getStudent(@RequestParam int subjectId) {
		return studentService.findStudentsBySubject(subjectId);
	}

	@GetMapping("/details")
	public List<Map<String, Object>> getAttendanceDetails(@RequestParam int studentId, @RequestParam String subjectName) {
	    List<Object[]> result = attendanceService.getAttendanceDetails(studentId, subjectName);
	    
	    List<Map<String, Object>> response = new ArrayList<>();
	    for (Object[] row : result) {
	        Map<String, Object> rowData = new HashMap<>();
	        rowData.put("date", row[0]);
	        rowData.put("attendanceon", row[1]);
	        rowData.put("takeattendancestatus", row[2]);
	        response.add(rowData);
	    }
	    return response;
	}



	@GetMapping("/getStudentsAttendance")
	public List<Map<String, Object>> getAttendanceByMasterId(@RequestParam int subjectId,
			@RequestParam int attendanceMasterId) {
		List<Object[]> students = attendanceRepository.findAttendanceAndSubjectByMasterIdAndSubjectId(subjectId,
				attendanceMasterId);

		if (students == null || students.isEmpty()) {
			return Collections.emptyList();
		}

		return students.stream().map(student -> {
			Map<String, Object> map = new HashMap<>();
			map.put("studentid", student[0]);
			map.put("entrollmentno", student[1]);
			map.put("fname", student[2] != null ? student[2] : "");
			map.put("mname", student[3] != null ? student[3] : "");
			map.put("lname", student[4] != null ? student[4] : "");
			map.put("attendancestatus", student[5] != null ? student[5] : "");

			return map;
		}).collect(Collectors.toList());
	}

	@GetMapping("/getAttendance")
	@ResponseBody
	public ResponseEntity<?> getAttendance(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in. Please log in first.");
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found for email: " + email);
		}

		Date sqlStartDate = Date.valueOf(startDate);
		Date sqlEndDate = Date.valueOf(endDate.plusDays(1));

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		List<TakeAttendance> attendanceList = takeAttendanceService.getAttendanceByDate(teacher, sqlStartDate,
				sqlEndDate);

		List<Map<String, String>> response = attendanceList.stream().map(attendance -> {
			Map<String, String> map = new HashMap<>();
			map.put("attendancemasterid", String.valueOf(attendance.getAttendancemasterid().getAttendancemasterid()));
			map.put("subjectid", String.valueOf(attendance.getAttendancemasterid().getSubject().getSubjectid()));
			map.put("programid", String.valueOf(attendance.getAttendancemasterid().getProgram().getProgramid()));
			map.put("semesterid", String.valueOf(attendance.getAttendancemasterid().getSemester().getSemesterid()));
			map.put("subjectCode", attendance.getAttendancemasterid().getSubject().getSubjectcode());
			map.put("subject", attendance.getAttendancemasterid().getSubject().getSubject());
			map.put("program", attendance.getAttendancemasterid().getProgram().getProgram());
			map.put("semester", attendance.getAttendancemasterid().getSemester().getSemester());

			map.put("date",
					attendance.getAttendanceMasterid().getDate() != null
							? formatter.format(attendance.getAttendanceMasterid().getDate())
							: "N/A");
			map.put("startTime",
					attendance.getAttendancemasterid().getTimetable() != null
							? attendance.getAttendancemasterid().getTimetable().getFormatStartTime()
							: "N/A");
			map.put("endTime",
					attendance.getAttendancemasterid().getTimetable() != null
							? attendance.getAttendancemasterid().getTimetable().getFormatEndTime()
							: "N/A");
			return map;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}

	@GetMapping("/teacherLeave/{leaveId}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getTeacherLeaveById(@PathVariable int leaveId) {
		Optional<TeacherLeave> teacherLeave = teacherLeaveRepository.findById(leaveId);

		if (teacherLeave.isPresent()) {
			Map<String, Object> response = new HashMap<>();
			response.put("leaveId", teacherLeave.get().getLeaveId());
			response.put("reason", teacherLeave.get().getReason());
			response.put("supportingDocumentPath", teacherLeave.get().getSupportingDocumentPath());

			Teacher teacher = teacherLeave.get().getLeaveBy();
			response.put("registrationid", (teacher != null) ? teacher.getRegistrationid() : null);

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/studentLeave/{leaveId}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getStudentLeaveById(@PathVariable int leaveId) {
		Optional<StudentLeave> studentLeave = studentLeaveRepository.findById(leaveId);

		if (studentLeave.isPresent()) {
			Map<String, Object> response = new HashMap<>();
			response.put("leaveId", studentLeave.get().getLeaveId());
			response.put("reason", studentLeave.get().getReason());
			response.put("supportingDocumentPath", studentLeave.get().getSupportingDocumentPath());

			Student student = studentLeave.get().getLeaveBy();
			response.put("entrollmentno", (student != null) ? student.getEntrollmentno() : null);

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/timetable/{programId}/{semesterId}")
	public ResponseEntity<List<Map<String, String>>> getTimetable(@PathVariable int programId,
			@PathVariable int semesterId) {
		List<Timetable> timetableEntries = timetableService.getTimetablesByProgramIdAndSemesterId(programId,
				semesterId);
		List<Map<String, String>> response = timetableEntries.stream().map(entry -> {
			Map<String, String> map = new HashMap<>();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedStartTime = entry.getStartTime().format(formatter);
			String formattedEndTime = entry.getEndTime().format(formatter);

			map.put("timetableId", String.valueOf(entry.getTimetableid()));
			map.put("day", entry.getDay());
			map.put("programId", String.valueOf(entry.getProgram().getProgramid()));
			map.put("semesterId", String.valueOf(entry.getSemester().getSemesterid()));
			map.put("program", entry.getProgram().getProgram());
			map.put("semester", entry.getSemester().getSemester());
			map.put("startTime", formattedStartTime);
			map.put("endTime", formattedEndTime);
			map.put("subject", entry.getSubject().getSubject());
			map.put("teacher", formatTeacherName(entry.getTeacher()));

			return map;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}

	@GetMapping("/timetable/teacher/{teacherId}")
	public ResponseEntity<List<Map<String, String>>> getTeacherTimetableByTeacherId(@PathVariable int teacherId) {
		List<Timetable> timetableEntries = timetableService.getTimetablesByTeacherId(teacherId);
		List<Map<String, String>> response = timetableEntries.stream().map(entry -> {
			Map<String, String> map = new HashMap<>();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedStartTime = entry.getStartTime().format(formatter);
			String formattedEndTime = entry.getEndTime().format(formatter);

			map.put("timetableId", String.valueOf(entry.getTimetableid()));
			map.put("day", entry.getDay());
			map.put("programId", String.valueOf(entry.getProgram().getProgramid()));
			map.put("semesterId", String.valueOf(entry.getSemester().getSemesterid()));
			map.put("program", entry.getProgram().getProgram());
			map.put("semester", entry.getSemester().getSemester());
			map.put("startTime", formattedStartTime);
			map.put("endTime", formattedEndTime);
			map.put("subject", entry.getSubject().getSubject());
			map.put("teacher", formatTeacherName(entry.getTeacher()));

			return map;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}

	@GetMapping("/user/department")
	public ResponseEntity<Map<String, Object>> getUserDepartment(HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not logged in"));
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "Teacher not found for email: " + email));
		}

		Department department = departmentService.findByTeacherId(teacher.getTeacherid());
		if (department == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Department not found"));
		}

		Map<String, Object> response = new HashMap<>();
		response.put("departmentId", department.getDepartmentid());
		response.put("departmentName", department.getDepartment());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/teachers/{departmentId}")
	public List<Map<String, Object>> getTeachers(@PathVariable int departmentId) {
		Department department = departmentService.findById(departmentId);
		if (department == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
		}

		List<Teacher> teachers = teacherService.findTeacherByDepartment(department);

		if (teachers == null || teachers.isEmpty()) {
			return new ArrayList<>();
		}

		List<Map<String, Object>> result = new ArrayList<>();

		for (Teacher teacher : teachers) {
			Map<String, Object> teacherMap = new HashMap<>();
			teacherMap.put("teacherid", teacher.getTeacherid());
			teacherMap.put("name", (teacher.getFname() != null ? teacher.getFname() : "") + " "
					+ (teacher.getMname() != null && !teacher.getMname().isEmpty() ? teacher.getMname() + " " : "")
					+ (teacher.getLname() != null ? teacher.getLname() : ""));

			result.add(teacherMap);
		}

		return result;
	}

	@GetMapping("/user/program")
	public ResponseEntity<Map<String, Object>> getUserProgram(HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not logged in"));
		}

		String email = user.getEmail();
		Teacher teacher = teacherService.getTeacherByEmail(email);

		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("error", "Teacher not found for email: " + email));
		}

		Program program = programService.findByTeacherId(teacher.getTeacherid());
		if (program == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Program not found"));
		}

		Map<String, Object> response = new HashMap<>();
		response.put("programId", program.getProgramid());
		response.put("programName", program.getProgram());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/subjects/{programId}/{semesterId}")
	public ResponseEntity<List<Map<String, Object>>> getSubjectsByProgram(@PathVariable int programId,
			@PathVariable int semesterId) {
		try {
			List<Subject> subjects = subjectService.findSubjectsByProgramAndBySemester(programId, semesterId);

			if (subjects == null || subjects.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
			}

			List<Map<String, Object>> subjectMapList = subjects.stream().map(subject -> {
				Map<String, Object> subjectMap = new HashMap<>();
				subjectMap.put("subjectid", subject.getSubjectid());
				subjectMap.put("name", subject.getSubject());
				return subjectMap;
			}).collect(Collectors.toList());

			return ResponseEntity.ok(subjectMapList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(List.of(Map.of("error", "Error fetching subjects")));
		}
	}

	private String formatTeacherName(Teacher teacher) {
		StringBuilder fullName = new StringBuilder();

		if (teacher.getFname() != null) {
			fullName.append(teacher.getFname());
		}
		if (teacher.getMname() != null && !teacher.getMname().isEmpty()) {
			fullName.append(" ").append(teacher.getMname());
		}
		if (teacher.getLname() != null) {
			fullName.append(" ").append(teacher.getLname());
		}

		return fullName.toString().trim();
	}

	@GetMapping("/getStudentsByProgram")
	@ResponseBody
	public List<Map<String, Object>> getStudentsByProgram(@RequestParam("programId") int programId) {
		List<Student> students = studentService.getStudentsByProgram(programId);
		List<Map<String, Object>> studentDataList = new ArrayList<>();

		for (Student student : students) {
			Map<String, Object> studentData = new HashMap<>();
			studentData.put("entrollmentno", student.getEntrollmentno());
			studentData.put("fname", student.getFname());
			studentData.put("mname", student.getMname());
			studentData.put("lname", student.getLname());
			studentData.put("email", student.getEmail());
			studentData.put("gvpemail", student.getGvpemail());
			studentData.put("contactnumber", student.getContactnumber());
			studentData.put("program", student.getProgram().getProgram());
			studentData.put("semester", student.getSemester().getSemester());
			studentData.put("active", student.isActive());

			Addmission admission = addmissionService.findByStudentId(student.getStudentid());
			if (admission != null) {
				studentData.put("dob", admission.getDob());
				studentData.put("address", admission.getAddress());
				studentData.put("image", admission.getImage());
			}

			studentDataList.add(studentData);
		}

		return studentDataList;
	}

	@PostMapping("/createTeacher")
	public String createTeacherUser(@RequestParam("facultyid") Faculty faculty,
			@RequestParam("departmentid") Department department, @RequestParam("fname") String fname,
			@RequestParam("mname") String mname, @RequestParam("lname") String lname,
			@RequestParam("personalemail") String personalemail, @RequestParam("contactnumber") String contactnumber,
			@RequestParam("dob") Date dob, @RequestParam("address") String address,
			@RequestParam("qualification") String qualification, @RequestParam("image") MultipartFile image) {

		try {
			String base64Image = convertToBase64(image);

			userService.createTeacherUser(fname, mname, lname, personalemail, contactnumber, dob, address,
					qualification, base64Image, faculty, department);

			return "Teacher user created successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create teacher user";
		}
	}

	@PostMapping("/createStudent")
	public String createStudentUser(@RequestParam("facultyid") Faculty faculty,
			@RequestParam("departmentid") Department department, @RequestParam("programid") Program program,
			@RequestParam("semesterid") Semester semester, @RequestParam("fname") String fname,
			@RequestParam("mname") String mname, @RequestParam("lname") String lname,
			@RequestParam("email") String email, @RequestParam("contactnumber") String contactnumber,
			@RequestParam("dob") Date dob, @RequestParam("address") String address,
			@RequestParam("image") MultipartFile image) {

		try {
			String base64Image = convertToBase64(image);

			userService.createStudentUser(faculty, department, program, semester, fname, mname, lname, email,
					contactnumber, dob, address, base64Image);

			return "Student user created successfully!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Failed to create student user";
		}
	}
}
