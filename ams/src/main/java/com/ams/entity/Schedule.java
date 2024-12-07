package com.ams.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleid")
    private int scheduleid;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "Schedule name is mandatory")
    @Size(min = 5, max = 255, message = "Schedule name must be between 5 and 255 characters")
    private String schedule;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "Details of schedule are mandatory")
    @Size(min = 10, max = 255, message = "Details must be between 10 and 255 characters")
    private String details;

    @Column(name = "day", nullable = false)
    private int day;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "starttime", nullable = false)
    private LocalTime startTime;

    @Column(name = "endtime", nullable = false)
    private LocalTime endTime;

    @Column(name = "createdon", nullable = false)
    private Date createdon;

    @Column(name = "updatedon")
    private Date updatedon;

    @Column(name = "createdby", nullable = false, columnDefinition = "VARCHAR(255)")
    private String createdBy;

    @Column(name = "modifiedby", columnDefinition = "VARCHAR(255)")
    private String modifiedBy;

    // Getters and setters...

    public int getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        return startTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public String getFormattedEndTime() {
        return endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
