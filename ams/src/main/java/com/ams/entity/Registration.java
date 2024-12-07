package com.ams.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "registration")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacherid", nullable = false)
	private int teacherid;

	@ManyToOne
	@JoinColumn(name = "facultyid", referencedColumnName = "facultyid")
	private Faculty facultyid;

	@ManyToOne
	@JoinColumn(name = "departmentid", referencedColumnName = "departmentid")
	private Department departmentid;

	@Column(name = "fname", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "First name is required")
	@Size(min = 3, max = 255, message = "First name must be between 3 and 255 characters")
	private String fname;

	@Column(name = "mname", nullable = false, columnDefinition = "VARCHAR(255)")
	@Size(max = 255, message = "Middle name must be less than 255 characters")
	private String mname;

	@Column(name = "lname", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Last name is required")
	@Size(min = 3, max = 255, message = "Last name must be between 3 and 255 characters")
	private String lname;

	@Column(name = "personalemail", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Personal email is required")
	@Email(message = "Personal email should be valid")
	private String personalemail;

	@Column(name = "gvpemail", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "GVP email is required")
	@Email(message = "GVP email should be valid")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gujaratvidyapith\\.org$", message = "GVP email should be from gujaratvidyapith.org domain")
	private String gvpemail;
	
	@Column(name = "contactnumber", nullable = false, columnDefinition = "CHAR(10)")
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Contact number should be 10 digits")
    private String contactnumber;

	@Column(name = "dob", nullable = false)
	private Date dob;

	@Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)")
	@Size(max = 255, message = "Address must be less than 255 characters")
	private String address;

	@Column(name = "qualification", nullable = false, columnDefinition = "VARCHAR(255)")
	@Size(max = 255, message = "Qualification must be less than 255 characters")
	private String qualification;

	@Column(name = "image", columnDefinition = "VARCHAR(255)")
	private String image;

	
	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public Faculty getFacultyid() {
		return facultyid;
	}

	public void setFacultyid(Faculty facultyid) {
		this.facultyid = facultyid;
	}

	public Department getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Department departmentid) {
		this.departmentid = departmentid;
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

	public String getPersonalemail() {
		return personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getGvpemail() {
		return gvpemail;
	}

	public void setGvpemail(String gvpemail) {
		this.gvpemail = gvpemail;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}