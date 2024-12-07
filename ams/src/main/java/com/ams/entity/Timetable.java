package com.ams.entity;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
@Table(name = "timetable")
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timetableid")
	private int timetableid;

	@ManyToOne
	@JoinColumn(name = "subjectid", referencedColumnName = "subjectid")
	private Subject subject;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(255)")
	@NotBlank(message = "Day name is mandatory")
	@Size(min = 5, max = 255, message = "Day name must be between 5 and 255 characters")
	private String day;

	@ManyToOne
	@JoinColumn(name = "teacherid", referencedColumnName = "teacherid")
	private Teacher teacher;

	@Column(name = "starttime", nullable = false)
	private LocalTime startTime;

	@Column(name = "endtime", nullable = false)
	private LocalTime endTime;

	@ManyToOne
	@JoinColumn(name = "programid", referencedColumnName = "programid")
	private Program program;

	@ManyToOne
	@JoinColumn(name = "semesterid", referencedColumnName = "semesterid")
	private Semester semester;

	@Column(name = "createdon", nullable = false)
	private Date createdon;

	@Column(name = "updatedon")
	private Date updatedon;

	@Column(name = "createdby", nullable = false, columnDefinition = "VARCHAR(255)")
	private String createdBy;

	@Column(name = "modifiedby", columnDefinition = "VARCHAR(255)")
	private String modifiedBy;

	public int getTimetableid() {
		return timetableid;
	}

	public void setTimetableid(int timetableid) {
		this.timetableid = timetableid;
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Teacher getTecaher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
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


	public String getFormattedStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(startTime);
    }

    public String getFormattedEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(endTime);
    }
    
    public String getFormatStartTime() {
        return startTime != null ? startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "N/A";
    }

    public String getFormatEndTime() {
        return endTime != null ? endTime.format(DateTimeFormatter.ofPattern("hh:mm a")) : "N/A";
    }

}
