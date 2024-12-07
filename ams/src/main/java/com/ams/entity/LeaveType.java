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
@Table(name = "leavetype")
public class LeaveType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leavetypeid")
	private int leaveTypeid;

	@Column(name = "leavetype", nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "LeaveType name is mandatory")
	@Size(min = 3, max = 255, message = "LeaveType name must be between 3 and 255 characters")
	private String leaveType;

	@Column(name = "active", nullable = false)
    private boolean active;
	
	public int getLeaveTypeid() {
		return leaveTypeid;
	}

	public void setLeaveTypeid(int leaveTypeid) {
		this.leaveTypeid = leaveTypeid;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}