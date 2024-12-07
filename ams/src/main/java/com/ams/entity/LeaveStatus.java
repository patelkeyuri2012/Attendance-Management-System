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
@Table(name = "leavestatus")
public class LeaveStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leavestatusid")
	private int leaveStatusid;

	@Column(name = "leavestatus", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "LeaveStatus name is mandatory")
	@Size(min = 3, max = 255, message = "LeaveStatus name must be between 3 and 255 characters")
	private String leaveStatus;

	public int getLeaveStatusid() {
		return leaveStatusid;
	}

	public void setLeaveStatusid(int leaveStatusid) {
		this.leaveStatusid = leaveStatusid;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

}