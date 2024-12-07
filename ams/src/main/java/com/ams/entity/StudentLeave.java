package com.ams.entity;

import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
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
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "studentleave")
public class StudentLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaveid")
    private int leaveId;

    @Column(name = "startdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Leave start date is required")
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Leave end date is required")
    private Date endDate;

    @ManyToOne
	@JoinColumn(name = "leavetypeid", referencedColumnName = "leavetypeid")
	private LeaveType leaveType;

    @Column(name = "reason", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Reason for leave is mandatory")
    private String reason;

    @Column(name = "supportingdocumentpath")
    private String supportingDocumentPath; 

    @ManyToOne
	@JoinColumn(name = "leaveby", referencedColumnName = "studentid")
	private Student leaveBy;
   
    @ManyToOne
	@JoinColumn(name = "leavestatusid", referencedColumnName = "leavestatusid")
	private LeaveStatus leaveStatus;


       public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSupportingDocumentPath() {
		return supportingDocumentPath;
	}

	public void setSupportingDocumentPath(String supportingDocumentPath) {
        this.supportingDocumentPath = supportingDocumentPath;
    }

	public Student getLeaveBy() {
		return leaveBy;
	}

	public void setLeaveBy(Student leaveBy) {
		this.leaveBy = leaveBy;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public long getTotalLeaveDays() {
        if (startDate != null && endDate != null) {
            return ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant()) + 1;
        }
        return 0;
    }
	
	public String getFileName() {
	    if (supportingDocumentPath != null) {
	        return Paths.get(supportingDocumentPath).getFileName().toString();
	    }
	    return null;
	}

}
