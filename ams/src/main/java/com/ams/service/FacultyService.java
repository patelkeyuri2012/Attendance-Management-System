package com.ams.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Faculty;
import com.ams.repository.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty createFaculty(String facultyName, String details, boolean active, String createdBy, String modifiedBy) {
        Faculty faculty = new Faculty();
        faculty.setFaculty(facultyName);
        faculty.setDetails(details);
        faculty.setActive(active);
        faculty.setCreatedon(new Date());
        faculty.setCreatedBy(createdBy);
        faculty.setUpdatedon(new Date());
        faculty.setModifiedBy(modifiedBy);
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(int facultyId, String facultyName, String details, boolean active, String modifiedBy) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid faculty Id:" + facultyId));

        faculty.setFaculty(facultyName);
        faculty.setDetails(details);
        faculty.setActive(active);
        faculty.setUpdatedon(new Date());
        faculty.setModifiedBy(modifiedBy);

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(int facultyId) {
        facultyRepository.deleteById(facultyId);
    }
 
    public Faculty getFacultyById(int facultyId) {
        return facultyRepository.findByFacultyid(facultyId);
    }

    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }
}
