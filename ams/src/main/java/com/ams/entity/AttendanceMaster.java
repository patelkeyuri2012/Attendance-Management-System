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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "attendancemaster")
public class AttendanceMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendancemasterid")
    private int attendancemasterid;

    @Column(name = "date", nullable = false)
    @NotNull(message = "Date is mandatory")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "programid", referencedColumnName = "programid")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "semesterid", referencedColumnName = "semesterid")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "departmentid", referencedColumnName = "departmentid")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacherid", referencedColumnName = "teacherid")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "timetableid", referencedColumnName = "timetableid")
    private Timetable timetable;

    public int getAttendancemasterid() {
		return attendancemasterid;
	}

	public void setAttendancemasterid(int attendancemasterid) {
		this.attendancemasterid = attendancemasterid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

}
