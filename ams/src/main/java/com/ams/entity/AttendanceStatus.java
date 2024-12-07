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
@Table(name = "attendancestatus")
public class AttendanceStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendancestatusid")
	private int attendanceStatusid;

	@Column(name = "attendancestatus", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "AttendanceStatus name is mandatory")
	@Size(min = 3, max = 255, message = "AttendanceStatus name must be between 3 and 255 characters")
	private String attendanceStatus;

	public int getAttendanceStatusid() {
		return attendanceStatusid;
	}

	public void setAttendanceStatusid(int attendanceStatusid) {
		this.attendanceStatusid = attendanceStatusid;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

}