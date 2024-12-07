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
@Table(name = "takeattendancestatus")
public class TakeAttendanceStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "takeattendancestatusid")
	private int takeAttendanceStatusid;

	@Column(name = "takeattendancestatus", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "TakeAttendanceStatus name is mandatory")
	@Size(min = 3, max = 255, message = "TakeAttendanceStatus name must be between 3 and 255 characters")
	private String takeAttendanceStatus;

	public int getTakeAttendanceStatusid() {
		return takeAttendanceStatusid;
	}

	public void setTakeAttendanceStatusid(int takeAttendanceStatusid) {
		this.takeAttendanceStatusid = takeAttendanceStatusid;
	}

	public String getTakeAttendanceStatus() {
		return takeAttendanceStatus;
	}

	public void setTakeAttendanceStatus(String takeAttendanceStatus) {
		this.takeAttendanceStatus = takeAttendanceStatus;
	}

}