package com.ams.entity;

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
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facultyid")
    private int facultyid;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "Faculty name is mandatory")
    @Size(min = 5, max = 255, message = "Faculty name must be between 5 and 255 characters")
    private String faculty;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "Details of faculty are mandatory")
    @Size(min = 10, max = 255, message = "Details must be between 10 and 255 characters")
    private String details;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "createdon", nullable = false)
    private Date createdon;

    @Column(name = "updatedon")
    private Date updatedon;

    @Column(name = "createdby", nullable = false, columnDefinition = "VARCHAR(255)")
    private String createdBy;

    @Column(name = "modifiedby", columnDefinition = "VARCHAR(255)")
    private String modifiedBy;

    // Getters and setters
    public int getFacultyid() {
        return facultyid;
    }

    public void setFacultyid(int facultyid) {
        this.facultyid = facultyid;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
