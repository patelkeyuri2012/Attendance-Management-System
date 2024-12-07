package com.ams.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ams.service.SemesterService;
import com.ams.service.StudentService;

@Component
public class SemesterActivationScheduler {

    @Autowired
    private SemesterService semesterService;
    
    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateSemesterStatus() {
        semesterService.updateSemesterActivationStatus();
    }
    
    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateSemesterAndStudentStatus() {
        semesterService.updateSemesterActivationStatus();
        studentService.updateStudentSemester();
    }
}