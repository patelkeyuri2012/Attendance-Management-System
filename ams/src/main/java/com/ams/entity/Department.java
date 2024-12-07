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
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "departmentid")
	private int departmentid;

	@ManyToOne
	@JoinColumn(name = "facultyid", referencedColumnName = "facultyid")
	private Faculty faculty;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Department is mandatory")
	@Size(min = 5, max = 255, message = "Department name must be grater than 5 characters")
	private String department;

	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Details of department is mandatory")
	@Size(min = 10, max = 255, message = "Details must be grater than 5 characters")
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

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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