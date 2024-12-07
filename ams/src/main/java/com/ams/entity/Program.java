package com.ams.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "program")
public class Program {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "programid")
	private int programid;

	@ManyToOne
	@JoinColumn(name = "facultyid", referencedColumnName = "facultyid")
	private Faculty faculty;

	@ManyToOne
	@JoinColumn(name = "departmentid", referencedColumnName = "departmentid")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "semesterid", referencedColumnName = "semesterid")
	private Semester semester;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Program is mandatory")
	@Size(min = 5, max = 255, message = "Program name must be greater than 5 characters")
	private String program;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Details are mandatory")
	@Size(min = 10, max = 255, message = "Details must be greater than 10 characters")
	private String details; 

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "createdon", nullable = false)
	private Date createdon;

	@Column(name = "updatedon", nullable = false)
	private Date updatedon;

	@Column(name = "createdby", nullable = false, columnDefinition = "VARCHAR(255)")
	private String createdBy;

	@Column(name = "modifiedby", nullable = false, columnDefinition = "VARCHAR(255)")
	private String modifiedBy;

	public int getProgramid() {
		return programid;
	}

	public void setProgramid(int programid) {
		this.programid = programid;
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

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
