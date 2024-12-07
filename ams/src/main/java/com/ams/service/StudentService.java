package com.ams.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Student;
import com.ams.entity.Subject;
import com.ams.entity.User;
import com.ams.entity.UserRole;
import com.ams.repository.RoleRepository;
import com.ams.repository.SemesterRepository;
import com.ams.repository.StudentRepository;
import com.ams.repository.SubjectRepository;
import com.ams.repository.UserRepository;
import com.ams.repository.UserRoleRepository;

@Service
public class StudentService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	public StudentRepository studentRepository;

	@Autowired
	public SubjectRepository subjectRepository;

	@Autowired
	public SemesterRepository semesterRepository;

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

	public void saveStudent(String entrollmentno, String firstName, String middleName, String lastName, String email,
			String contactnumber, int facultyId, int departmentId, int programId, int semesterId, boolean active,
			String createdBy, String modifiedBy, String randomPassword) {
		Student student = new Student();

		student.setEntrollmentno(entrollmentno);
		student.setFname(firstName);
		student.setMname(middleName);
		student.setLname(lastName);
		student.setEmail(email);
		student.setContactnumber(contactnumber);
		student.setFaculty(facultyService.getFacultyById(facultyId));
		student.setDepartment(departmentService.getDepartmentById(departmentId));
		student.setProgram(programService.getProgramById(programId));
		student.setSemester(semesterService.getSemesterById(semesterId));
		student.setPassword(randomPassword);
		student.setActive(active);
		student.setCreatedon(new Date());
		student.setCreatedby(createdBy);
		student.setModifiedon(new Date());
		student.setModifiedby(modifiedBy);
		student.setGvpemail("temporary@gujaratvidyapith.org");

		List<Subject> subjects = subjectService.getSubjectsByProgramAndSemester(programId, semesterId);

		List<Subject> nonElectiveSubjects = subjects.stream().filter(subject -> !subject.isElective())
				.collect(Collectors.toList());

		student.setSubjects(nonElectiveSubjects);

		student = studentRepository.save(student);

		String studentId = String.format("%02d", student.getStudentid());
		String gvpemail = "2123080" + studentId + ".gvp@gujaratvidyapith.org";
		student.setGvpemail(gvpemail);

		studentRepository.save(student);

		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(gvpemail);
			user.setPassword(randomPassword);
		}
		user.setUsername(firstName + " " + middleName + " " + lastName);
		user.setRole(roleRepository.findByName("STUDENT"));

		userRepository.save(user);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(user.getRole());

		userRoleRepository.save(userRole);
	}

	public Student updateStudent(int studentId, String firstName, String middleName, String lastName, String email,
			String contactnumber, int facultyId, int departmentId, int programId, int semesterId, boolean active,
			String modifiedBy) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + studentId));

		Faculty faculty = facultyService.getFacultyById(facultyId);
		Department department = departmentService.getDepartmentById(departmentId);
		Program program = programService.getProgramById(programId);
		Semester semester = semesterService.getSemesterById(semesterId);

		student.setFname(firstName);
		student.setMname(middleName);
		student.setLname(lastName);
		student.setEmail(email);
		student.setContactnumber(contactnumber);
		student.setFaculty(faculty);
		student.setDepartment(department);
		student.setProgram(program);
		student.setSemester(semester);
		student.setActive(active);
		student.setModifiedon(new Date());
		student.setModifiedby(modifiedBy);

		List<Subject> subjects = subjectService.getSubjectsByProgramAndSemester(programId, semesterId);

		List<Subject> nonElectiveSubjects = subjects.stream().filter(subject -> !subject.isElective())
				.collect(Collectors.toList());

		student.setSubjects(nonElectiveSubjects);

		return studentRepository.save(student);
	}

	public void deleteStudent(int studentId) {
		studentRepository.deleteById(studentId);
	}

	public void updateStudentSemester() {

		List<Student> students = studentRepository.findAll();

		for (Student student : students) {
			Semester semester = student.getSemester();
			int semesterNumber;

			try {
				semesterNumber = Integer.parseInt(semester.getSemester().replaceAll("[^0-9]", ""));
			} catch (NumberFormatException e) {
				continue;
			}

					if (!semester.isActive()) {
				while (semesterNumber <= 6) {
					semesterNumber++;
					Semester nextSemester = semesterRepository.findBySemesterid(semesterNumber);

					if (nextSemester != null && nextSemester.isActive()) {
						student.setSemester(nextSemester);
						nextSemester.setActive(true);
						break;
					}
				}
			} else {
				if (semesterNumber == 6) {
					student.setActive(false);
					student.setSemester(semester);
				} else {
					semesterNumber++;

					Semester nextSemester = semesterRepository.findBySemesterid(semesterNumber);
					
					if (nextSemester != null && nextSemester.isActive()) {
						student.setSemester(nextSemester);
						nextSemester.setActive(true);
					} 
				}
			}
		}

		studentRepository.saveAll(students);
	}

	public Student getStudentById(int studentId) {
		return studentRepository.findByStudentid(studentId);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public List<Student> findStudentsByDepartment(String departmentName) {
		return studentRepository.findByDepartmentName(departmentName);
	}

	public List<Student> findStudentsByDepartmentId(Integer departmentId) {
		return studentRepository.findByDepartmentId(departmentId);
	}

	public List<Student> getStudentsByProgram(int programId) {
		return studentRepository.findByProgramProgramid(programId);
	}

	public int getStudentCountByProgramAndSemester(int programid, int semesterid) {
		return studentRepository.countByProgramAndSemester(programid, semesterid);
	}

	public int getActiveStudentCountByProgramAndSemester(int programid, int semesterid) {
		return studentRepository.countByProgramAndSemesterByActive(programid, semesterid);
	}

	public int getDeactiveStudentCountByProgramAndSemester(int programid, int semesterid) {
		return studentRepository.countByProgramAndSemesterByDeactive(programid, semesterid);
	}

	public List<Student> findStudentsByProgramSemesterAndSubject(int programId, int semesterId, int subjectId) {
		return studentRepository.findStudentsByProgramSemesterAndSubject(programId, semesterId, subjectId);
	}
	
	public List<Student> findStudentsBySubject(int subjectId) {
        return studentRepository.findStudentsBySubject(subjectId);
    }
	
	public Student findStudentById(Integer studentId) {
		return studentRepository.findById(studentId).orElse(null);
	}

	public List<Subject> getSubjectsByIds(List<Integer> subjectIds) {
		return subjectRepository.findAllById(subjectIds);
	}

	public void addStudentSubject(List<Integer> studentIds, Integer subjectId) {
		Subject newSubject = subjectService.getSubjectById(subjectId);

		if (newSubject == null) {
			System.out.println("Subject with ID " + subjectId + " does not exist.");
			return;
		}

		for (Integer studentId : studentIds) {
			Student student = findStudentById(studentId);
			if (student != null) {
				List<Subject> existingSubjects = student.getSubjects();

				System.out.println(existingSubjects);
				if (!existingSubjects.contains(newSubject)) {
					existingSubjects.add(newSubject);

					studentRepository.save(student);
				}
			}
		}
	}

	public Student getStudentByEmail(String email) {
	    return studentRepository.findBygvpemail(email);
	}

	public List<Student> getStudentsByProgram(Program program) {
		return studentRepository.findByProgram(program);
	}


}
