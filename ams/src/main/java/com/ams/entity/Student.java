package com.ams.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentid", nullable = false)
	private int studentid;

	@Column(name = "entrollmentno", nullable = false)
	private String entrollmentno;

	@Column(name = "fname", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 255, message = "First name must be between 3 and 255 characters")
	private String fname;

	@Column(name = "mname", columnDefinition = "VARCHAR(255)")
	@Size(min = 2, max = 255, message = "Middle name must be less than 255 characters")
	private String mname;

	@Column(name = "lname", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 255, message = "Last name must be between 3 and 255 characters")
	private String lname;

	@Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@Column(name = "gvpemail", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "GVP email is required")
	@Email(message = "GVP email should be valid")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gujaratvidyapith\\.org$", message = "GVP email should be from gujaratvidyapith.org domain")
	private String gvpemail;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "contactnumber", nullable = false, columnDefinition = "CHAR(10)")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Contact number should be 10 digits")
    private String contactnumber;

	@ManyToOne
	@JoinColumn(name = "facultyid", referencedColumnName = "facultyid")
	private Faculty faculty;

	@ManyToOne
	@JoinColumn(name = "departmentid", referencedColumnName = "departmentid")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "programid", referencedColumnName = "programid")
	private Program program;
	
	@ManyToOne
	@JoinColumn(name = "semesterid", referencedColumnName = "semesterid")
	private Semester semester;

	@ManyToMany
	@JoinTable(name = "student_subject", joinColumns = @JoinColumn(name = "studentid"), inverseJoinColumns = @JoinColumn(name = "subjectid"))
	private List<Subject> subjects;
	
	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "createdby", nullable = false, columnDefinition = "VARCHAR(255)")
	private String createdby;

	@Column(name = "modifiedby", nullable = false, columnDefinition = "VARCHAR(255)")
	private String modifiedby;

	@Column(name = "createdon", nullable = false)
	private Date createdon;

	@Column(name = "modifiedon", nullable = false)
	private Date modifiedon;

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getEntrollmentno() {
		return entrollmentno;
	}

	public void setEntrollmentno(String entrollmentno) {
		this.entrollmentno = entrollmentno;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getGvpemail() {
		return gvpemail;
	}

	public void setGvpemail(String gvpemail) {
		this.gvpemail = gvpemail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Date getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
}