package com.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "semester")
public class Semester {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "semesterid")
	private int semesterid;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Semester name is mandatory")
	@Size(min = 3, max = 255, message = "Semester name must be between 3 and 255 characters")
	private String semester;

	@Column(name = "active", nullable = false)
    private boolean active;
	
	public int getSemesterid() {
		return semesterid;
	}

	public void setSemesterid(int semesterid) {
		this.semesterid = semesterid;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}