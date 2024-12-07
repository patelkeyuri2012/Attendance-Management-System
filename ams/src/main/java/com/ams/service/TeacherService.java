package com.ams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Subject;
import com.ams.entity.Teacher;
import com.ams.entity.User;
import com.ams.entity.UserRole;
import com.ams.repository.ProgramRepository;
import com.ams.repository.RoleRepository;
import com.ams.repository.TeacherRepository;
import com.ams.repository.UserRepository;
import com.ams.repository.UserRoleRepository;

@Service
public class TeacherService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
    
	@Autowired
	public TeacherRepository teacherRepository;
	
	@Autowired
	public ProgramRepository programRepository;

	@Autowired
	public FacultyService facultyService;

	@Autowired
	public DepartmentService departmentService;

	@Autowired
	public ProgramService programService;

	@Autowired
	public SemesterService semesterService;

	@Autowired
	public SubjectService subjectService;
	

	public void saveTeacher(String registrationId, String firstName, String middleName, String lastName, String email,
			String contactNo, String qualification, int facultyId, int departmentId, List<Program> programs, List<Semester> semesters,
			List<Subject> subjects, boolean active, String createdBy, String modifiedBy, String randomPassword) {
		Teacher teacher = new Teacher();

		teacher.setRegistrationid(registrationId);
		teacher.setFname(firstName);
		teacher.setMname(middleName);
		teacher.setLname(lastName);
		teacher.setEmail(email);
		teacher.setContactno(contactNo);
		teacher.setQualification(qualification);
		teacher.setFaculty(facultyService.getFacultyById(facultyId));
		teacher.setDepartment(departmentService.getDepartmentById(departmentId));
		teacher.setPrograms(programs);
		teacher.setSemesters(semesters);
		teacher.setSubjects(subjects);
		teacher.setPassword(randomPassword);
		teacher.setActive(active);
		teacher.setCreatedon(new Date());
		teacher.setCreatedby(createdBy);
		teacher.setModifiedon(new Date());
		teacher.setModifiedby(modifiedBy);

		teacherRepository.save(teacher);
		
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setPassword(randomPassword);
		}
		user.setUsername(firstName + " " + middleName + " " + lastName);
		user.setRole(roleRepository.findByName("TEACHER"));

		userRepository.save(user);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(user.getRole());

		userRoleRepository.save(userRole);
	}
	
	public Teacher updateTeacher(int teacherId, String firstName, String middleName, String lastName, String email,
	        String contactNo, String qualification, int facultyId, int departmentId, List<Program> programs, List<Semester> semesters,
	        List<Subject> subjects, boolean active, String modifiedBy) {

	    Teacher teacher = teacherRepository.findById(teacherId)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid Teacher Id:" + teacherId));

	    Faculty faculty = facultyService.getFacultyById(facultyId);
	    Department department = departmentService.getDepartmentById(departmentId);
	    
	    teacher.setFname(firstName);
	    teacher.setMname(middleName);
	    teacher.setLname(lastName);
	    teacher.setEmail(email);
	    teacher.setContactno(contactNo);
	    teacher.setQualification(qualification);
	    teacher.setFaculty(faculty);
	    teacher.setDepartment(department);
	    teacher.setPrograms(programs);
	    teacher.setSemesters(semesters);
	    teacher.setSubjects(subjects);
	    teacher.setActive(active);
	    teacher.setModifiedon(new Date());
	    teacher.setModifiedby(modifiedBy);

	    return teacherRepository.save(teacher);
	}

	public void deleteTeacher(int teacherId) {
		teacherRepository.deleteById(teacherId);
	}

	public Teacher getTeacherById(int teacherId) {
		return teacherRepository.findByTeacherid(teacherId);
	}
	
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

	public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
	
	public List<Program> getProgramsByTeacherId(int teacherId) {
	    Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
	    if (teacher != null) {
	        return teacher.getPrograms();
	    }
	    return teacherRepository.getProgramsByteacherid(teacherId); 
	}
	
	public List<Semester> getSemestersByTeacherId(int teacherId) {
	    Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
	    if (teacher != null) {
	        return teacher.getSemesters();
	    }
	    return teacherRepository.getSemestersByteacherid(teacherId); 
	}
	
	public Teacher getTeacherByEmail(String email) {
	    return teacherRepository.findByEmail(email);
	}
	
	public List<Program> getProgramsByTeacherDepartment(int teacherId) {
	    Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));

	    Department department = teacher.getDepartment();

	    return programRepository.findAllByDepartment(department); 
	}

	public List<Teacher> findTeacherByDepartmentAndFaculty(Department department, Faculty faculty) {
		return teacherRepository.findTeacherByDepartmentAndFaculty(department, faculty);
	}

	public List<Teacher> findTeacherByDepartment(Department teacherDepartment) {
		return teacherRepository.findAllByDepartment(teacherDepartment);
	}

	public List<Teacher> findTeachersByProgramAndSemester(Program program, Semester semester) {
		return teacherRepository.findTeachersByProgramAndSemester(program, semester);
	}
	
}
