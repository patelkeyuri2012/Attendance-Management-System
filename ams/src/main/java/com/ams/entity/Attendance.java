package com.ams.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendanceid")
    private int attendanceid;

    @ManyToOne
    @JoinColumn(name = "attendancemasterid", referencedColumnName = "attendancemasterid")
    private AttendanceMaster attendancemaster; 

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "studentid")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "teacherid", referencedColumnName = "teacherid")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "attendancestatusid", referencedColumnName = "attendancestatusid")
    private AttendanceStatus attendancestatus;

    @ManyToOne
    @JoinColumn(name = "leaveid", referencedColumnName = "leaveid")
    private StudentLeave leave;
    
   public int getAttendanceid() {
		return attendanceid;
	}

	public void setAttendanceid(int attendanceid) {
		this.attendanceid = attendanceid;
	}

	public AttendanceMaster getAttendancemaster() {
		return attendancemaster;
	}

	public void setAttendancemaster(AttendanceMaster attendancemaster) {
		this.attendancemaster = attendancemaster;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public AttendanceStatus getStatus() {
		return attendancestatus;
	}

	public void setStatus(AttendanceStatus attendancemaster) {
		this.attendancestatus = attendancemaster;
	}

	public StudentLeave getLeave() {
		return leave;
	}

	public void setLeave(StudentLeave leave) {
		this.leave = leave;
	}
}
