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

@Entity
@Table(name = "takeattendance")
public class TakeAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "takeattendanceid")
	private int takeattendanceid;

	@ManyToOne
	@JoinColumn(name = "attendancemasterid", referencedColumnName = "attendancemasterid")
	private AttendanceMaster attendancemasterid;

	@ManyToOne
	@JoinColumn(name = "takeattendancestatusid", referencedColumnName = "takeattendancestatusid")
	private TakeAttendanceStatus takeattendancestatus;

	@Column(name = "attendanceon", nullable = false)
	private Date attendanceon;

	@ManyToOne
	@JoinColumn(name = "attendanceby", referencedColumnName = "teacherid")
	private Teacher attendanceby;

	public int getTakeattendanceid() {
		return takeattendanceid;
	}

	public void setTakeattendanceid(int takeattendanceid) {
		this.takeattendanceid = takeattendanceid;
	}

	public AttendanceMaster getAttendanceMasterid() {
		return attendancemasterid;
	}

	public void setAttendanceMasterid(AttendanceMaster attendancemasterid) {
		this.attendancemasterid = attendancemasterid;
	}

	public AttendanceMaster getAttendancemasterid() {
		return attendancemasterid;
	}

	public void setAttendancemasterid(AttendanceMaster attendancemasterid) {
		this.attendancemasterid = attendancemasterid;
	}

	public TakeAttendanceStatus getTakeattendancestatus() {
		return takeattendancestatus;
	}

	public void setTakeattendancestatus(TakeAttendanceStatus takeattendancestatus) {
		this.takeattendancestatus = takeattendancestatus;
	}

	public Teacher getAttendanceby() {
		return attendanceby;
	}

	public void setAttendanceby(Teacher attendanceby) {
		this.attendanceby = attendanceby;
	}

	public Date getAttendanceon() {
		return attendanceon;
	}

	public void setAttendanceon(Date attendanceon) {
		this.attendanceon = attendanceon;
	}

	public Teacher getAttendanceBy() {
		return attendanceby;
	}

	public void setAttendanceBy(Teacher attendanceby) {
		this.attendanceby = attendanceby;
	}
}