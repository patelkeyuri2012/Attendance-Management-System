package com.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ams.entity.Addmission;
import com.ams.entity.Admin;
import com.ams.entity.Department;
import com.ams.entity.Faculty;
import com.ams.entity.Program;
import com.ams.entity.Registration;
import com.ams.entity.Semester;
import com.ams.entity.Teacher;
import com.ams.entity.Student;
import com.ams.entity.User;
import com.ams.entity.UserRole;
import com.ams.repository.AddmissionRepository;
import com.ams.repository.AdminRepository;
import com.ams.repository.HODRepository;
import com.ams.repository.RegistrationRepository;
import com.ams.repository.RoleRepository;
import com.ams.repository.StudentRepository;
import com.ams.repository.TeacherRepository;
import com.ams.repository.UserRepository;
import com.ams.repository.UserRoleRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private OtpService otpService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private HODRepository hodRepository;

	@Autowired
	private RegistrationRepository teacherRepository;

	@Autowired
	private TeacherRepository teachersRepository;

	@Autowired
	private AddmissionRepository studentRepository;

	@Autowired
	private StudentRepository studentsRepository;

	public Object getUserDetailsByRole(User user) {
		String email = user.getEmail();
		if (email == null) {
			throw new IllegalArgumentException("User email cannot be null");
		}

		switch (user.getRole().getName().toUpperCase()) {
		case "ADMIN":
			return adminRepository.findByemail(email)
					.orElseThrow(() -> new IllegalArgumentException("Admin not found with email: " + email));
		case "HOD":
			return hodRepository.findBygvpemail(email)
					.orElseThrow(() -> new IllegalArgumentException("HOD not found with email: " + email));
		case "TEACHER":
			return teacherRepository.findBygvpemail(email)
					.orElseThrow(() -> new IllegalArgumentException("Teacher not found with email: " + email));
		case "STUDENT":
			Optional<Addmission> addmissionOptional = studentRepository.findBygvpemail(email);
			if (addmissionOptional.isPresent()) {
				Addmission addmission = addmissionOptional.get();
				Student student = studentsRepository.findByStudentid(addmission.getStudentid());

				Map<String, Object> studentDetails = new HashMap<>();
				studentDetails.put("addmission", addmission);
				studentDetails.put("student", student);
				return studentDetails;
			} else {
				throw new IllegalArgumentException("Student details not found for email: " + email);
			}
		default:
			throw new IllegalArgumentException("Invalid role: " + user.getRole().getName());
		}
	}

	public Admin getUserByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public void createAdminUser(String fname, String mname, String lname, String email, String password,
			String qualification, String contactno, String imagePath) {

		Admin admin = new Admin();
		admin.setFname(fname);
		admin.setMname(mname);
		admin.setLname(lname);
		admin.setEmail(email);
		admin.setPassword(password);
		admin.setQualification(qualification);
		admin.setContactno(contactno);
		admin.setImage(imagePath);

		adminRepository.save(admin);

		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
		}
		user.setUsername(fname + " " + mname + " " + lname);
		user.setRole(roleRepository.findByName("ADMIN"));

		userRepository.save(user);

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(user.getRole());

		userRoleRepository.save(userRole);
	}

	public void createTeacherUser(String fname, String mname, String lname, String personalemail, String contactnumber,
			Date dob, String address, String qualification, String imagePath, Faculty faculty, Department department) {

		try {
			if (!contactnumber.matches("^[6789]\\d{9}$")) {
				throw new IllegalArgumentException("Contact number should be 10 digits");
			}

			Registration teacher = new Registration();
			teacher.setFname(fname);
			teacher.setMname(mname);
			teacher.setLname(lname);
			teacher.setPersonalemail(personalemail);
			teacher.setContactnumber(contactnumber);
			teacher.setDob(dob);
			teacher.setAddress(address);
			teacher.setQualification(qualification);
			teacher.setImage(imagePath);
			teacher.setFacultyid(faculty);
			teacher.setDepartmentid(department);
			teacher.setGvpemail("temporary@gujaratvidyapith.org");

			teacher = teacherRepository.save(teacher);

			String teacherId = String.format("%02d", teacher.getTeacherid());
			String gvpemail = fname + teacherId + ".gvp@gujaratvidyapith.org";
			teacher.setGvpemail(gvpemail);

			teacherRepository.save(teacher);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createStudentUser(Faculty faculty, Department department, Program program, Semester semester,
			String fname, String mname, String lname, String email, String contactnumber, Date dob, String address,
			String imagePath) {

		try {
			if (!contactnumber.matches("^[6789]\\d{9}$")) {
				throw new IllegalArgumentException("Contact number should be 10 digits");
			}

			Addmission student = new Addmission();
			student.setEntrollmentno("212308000");
			student.setFacultyid(faculty);
			student.setDepartmentid(department);
			student.setProgramid(program);
			student.setSemesterid(semester);
			student.setFname(fname);
			student.setMname(mname);
			student.setLname(lname);
			student.setEmail(email);
			student.setContactnumber(contactnumber);
			student.setDob(dob);
			student.setAddress(address);
			student.setImage(imagePath);

			student = studentRepository.save(student);

			String studentId = String.format("%02d", student.getStudentid());
			String entrollmentno = "2123080" + studentId;
			student.setEntrollmentno(entrollmentno);

			String gvpemail = "2123080" + studentId + ".gvp@gujaratvidyapith.org";
			student.setGvpemail(gvpemail);

			studentRepository.save(student);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Registration updateProfile(int teacherId, String imagePath, String firstName, String middleName,
			String lastName, Date dob, String contactNo, String email, String address) {

		Registration registration = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Teacher ID: " + teacherId));

		registration.setFname(firstName);
		registration.setMname(middleName);
		registration.setLname(lastName);
		registration.setDob(dob);
		registration.setContactnumber(contactNo);
		registration.setPersonalemail(email);
		registration.setAddress(address);
		registration.setImage(imagePath);

		Registration updatedRegistration = teacherRepository.save(registration);

		Teacher teacher = teachersRepository.findByTeacherid(teacherId);
		if (teacher != null) {
			teacher.setFname(firstName);
			teacher.setMname(middleName);
			teacher.setLname(lastName);
			teacher.setContactno(contactNo);
			teachersRepository.save(teacher);
		}

		return updatedRegistration;
	}

	public Addmission updateProfiles(int studentId, String imagePath, String firstName, String middleName,
			String lastName, Date dob, String contactNo, String email, String address) {

		Addmission addmission = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Student ID: " + studentId));

		addmission.setFname(firstName);
		addmission.setMname(middleName);
		addmission.setLname(lastName);
		addmission.setDob(dob);
		addmission.setContactnumber(contactNo);
		addmission.setEmail(email);
		addmission.setAddress(address);
		addmission.setImage(imagePath);

		Addmission updatedAddmission = studentRepository.save(addmission);

		Student student = studentRepository.fetchStudentByStudentid(studentId);
		if (student != null) {
			student.setFname(firstName);
			student.setMname(middleName);
			student.setLname(lastName);
			student.setContactnumber(contactNo);
			studentsRepository.save(student);
		}

		return updatedAddmission;
	}

	public String saveImage(MultipartFile image) {
		try {
			String originalFileName = image.getOriginalFilename();

			if (originalFileName == null || !originalFileName.contains(".")) {
				throw new IllegalArgumentException("Invalid file name: no extension found.");
			}

			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = "user_" + timeStamp + fileExtension;

			Path path = Paths.get("src/main/resources/static/assets/img/" + fileName);

			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			return "/assets/img/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean sendOtp(String email) {
		if (userRepository.existsByEmail(email)) {
			return otpService.sendOtp(email);
		} else {
			return false;
		}
	}

	public boolean verifyOtp(String email, String otp) {
		return otpService.verifyOtp(email, otp);
	}

	public boolean resetPassword(String email, String newPassword) {
		if (userRepository.existsByEmail(email)) {
			userRepository.updatePasswordByEmail(email, newPassword);

			boolean isAdmin = userRepository.isAdmin(email);
			boolean isTeacher = userRepository.isTeacher(email);
			boolean isStudent = userRepository.isAdmission(email);

			if (isAdmin) {
				userRepository.updateAdminPasswordByEmail(email, newPassword);
			}

			if (isTeacher) {
				userRepository.updateTeacherPasswordByEmail(email, newPassword);
			}

			if (isStudent) {
				userRepository.updateStudentPasswordByEmail(email, newPassword);
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean changePassword(String email, String oldPassword, String newPassword) {
		User user = userRepository.findByEmail(email);

		if (user.getPassword().equals(oldPassword)) {
			userRepository.updatePasswordByEmail(email, newPassword);

			if (userRepository.isAdmin(email)) {
				userRepository.updateAdminPasswordByEmail(email, newPassword);
			}

			if (userRepository.isTeacher(email)) {
				userRepository.updateTeacherPasswordByEmail(email, newPassword);
			}

			if (userRepository.isAdmission(email)) {
				userRepository.updateStudentPasswordByEmail(email, newPassword);
			}

		}
		return false;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User getUserByUserid(Integer userId) {
		return userRepository.findByUserid(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid User ID: " + userId));
	}

	public void blockUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
		user.setBlocked(true);
		userRepository.save(user);
	}

	public void unblockUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
		user.setBlocked(false);
		userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String getExistingHODImagePath(int studentId) {
		Registration hod = hodRepository.findByTeacherid(studentId);
		if (hod != null) {
			return hod.getImage();
		}
		return null;
	}

	public String getExistingTeacherImagePath(int studentId) {
		Registration teacher = teacherRepository.findByTeacherid(studentId);
		if (teacher != null) {
			return teacher.getImage();
		}
		return null;
	}

	public String getExistingStudentImagePath(int studentId) {
		Addmission student = studentRepository.findByStudentid(studentId);
		if (student != null) {
			return student.getImage();
		}
		return null;
	}
}
