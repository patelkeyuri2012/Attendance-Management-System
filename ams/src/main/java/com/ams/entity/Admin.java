package com.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminid;

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

	@Column(name = "email", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
	private String password;

	@Column(name = "qualification", nullable = false, columnDefinition = "VARCHAR(255)")
	@Size(max = 255, message = "Qualification must be less than 255 characters")
	private String qualification;

	@Column(name = "contactno", nullable = false, columnDefinition = "CHAR(10)")
	@Pattern(regexp = "^[6789]\\d{9}$", message = "Contact number should start with 7, 8, or 9 and be 10 digits long")
	private String contactno;

	@Column(name = "image", columnDefinition = "VARCHAR(255)")
	private String image;

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
