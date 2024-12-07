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
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subjectid")
	private int subjectid;

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

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "subjectcode is mandatory")
	@Size(min = 3, max = 255, message = "subjectcode name must be grater than 3 characters")
	private String subjectcode;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "subject is mandatory")
	@Size(min = 5, max = 255, message = "subject name must be grater than 5 characters")
	private String subject;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Details is mandatory")
	@Size(min = 10, max = 255, message = "Details must be grater than 5 characters")
	private String details;

	@Column(name = "elective", nullable = false)
	private boolean elective;

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

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
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

	public String getSubjectcode() {
		return subjectcode;
	}

	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isElective() {
		return elective;
	}

	public void setElective(boolean elective) {
		this.elective = elective;
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