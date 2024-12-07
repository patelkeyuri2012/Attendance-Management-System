package com.ams.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Semester;
import com.ams.repository.SemesterRepository;

@Service
public class SemesterService {

	@Autowired
	private SemesterRepository semesterRepository;

	public void createSemester(String semesterName) {
		Semester semester = new Semester();
		semester.setSemester(semesterName);
		semesterRepository.save(semester);
	}
	
	public void updateSemesterActivationStatus() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        boolean activateEven = (currentMonth >= 1 && currentMonth <= 6);  
        boolean activateOdd = (currentMonth >= 7 && currentMonth <= 12);  

        List<Semester> semesters = semesterRepository.findAll();

        for (Semester semester : semesters) {
            int semesterNumber;
            try {
                semesterNumber = Integer.parseInt(semester.getSemester().replaceAll("[^0-9]", ""));
            } catch (NumberFormatException e) {
                continue;
            }

            if ((semesterNumber % 2 == 0 && activateEven) || (semesterNumber % 2 != 0 && activateOdd)) {
                semester.setActive(true);
            } else {
                semester.setActive(false);
            }
        }
        
        semesterRepository.saveAll(semesters);
    }
	
	public Semester getSemesterById(int semesterId) {
		return semesterRepository.findBySemesterid(semesterId);
	}
	
	 public List<Semester> findAll() {
	        return semesterRepository.findAll();
	    }

	public List<Semester> getSemesterByIds(List<Integer> semesterIds) {
		return semesterRepository.findAllById(semesterIds);
	}

	public List<Semester> findAllByIsActive(boolean active) {
		 return semesterRepository.findAllByActive(active);
	}

	public Semester findById(int semesterId) {
		return semesterRepository.findBySemesterid(semesterId);
	}
	
	public List<Semester> findAllByProgram(int programId) {
	    return semesterRepository.findAllByProgram(programId);
	}
	
}