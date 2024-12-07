package com.ams.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Subject;
import com.ams.entity.Teacher;
import com.ams.service.ProgramService;
import com.ams.service.SemesterService;
import com.ams.service.SubjectService;
import com.ams.service.TeacherService;
import com.ams.service.TimetableService;

@Controller
public class TimetableController {

	@Autowired
	private TimetableService timetableService;
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private SemesterService semesterService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private SubjectService subjectService;

	@PostMapping("/addTimetables")
	public String addTimetable(
	        @RequestParam("subject") Integer subjectId,
	        @RequestParam("program") Integer programId,
	        @RequestParam("semester") Integer semesterId,
	        @RequestParam("startTime") LocalTime startTime,
	        @RequestParam("endTime") LocalTime endTime,
	        @RequestParam("day") String day,
	        @RequestParam("teacher") Integer teacherId,
	        @RequestParam(value = "createdBy", required = false) String createdBy,
	        @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

	    if (createdBy == null) createdBy = "defaultUser";
	    if (modifiedBy == null) modifiedBy = "defaultUser";

	    try {
	        Subject subject = subjectService.getSubjectById(subjectId);
	        Program program = programService.getProgramById(programId);
	        Semester semester = semesterService.getSemesterById(semesterId);
	        Teacher teacher = teacherService.getTeacherById(teacherId);

	        timetableService.createTimetable(subject, program, semester, day, teacher, startTime, endTime, createdBy, modifiedBy);
	    } catch (Exception e) {
	        System.err.println("Error occurred: " + e.getMessage());
	    }
	    return "redirect:/HOD/schedules.html";
	}


	@PostMapping("/updateTimetables")
	public String updateTimetable(
			@RequestParam("timetableid") Integer timetableId,
	        @RequestParam("subject") Integer subjectId, @RequestParam("day") String day,
	        @RequestParam("program") Integer programId, @RequestParam("semester") Integer semesterId,
	        @RequestParam("teacher") Integer teacherId,
	        @RequestParam("startTime") LocalTime startTime, @RequestParam("endTime") LocalTime endTime,
	        @RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

	    if (modifiedBy == null) {
	        modifiedBy = "defaultUser";
	    }
	    
	    try {
	    
	    Program program = programService.getProgramById(programId);
	    Semester semester = semesterService.getSemesterById(semesterId);
	    Teacher teacher = teacherService.getTeacherById(teacherId);
	    Subject subject = subjectService.getSubjectById(subjectId);
	  
	    timetableService.updateTimetable(timetableId, subject, program, semester, day, teacher, startTime, endTime, modifiedBy);
	    
	    } catch (Exception e) {
	        System.err.println("Error occurred: " + e.getMessage());
	    }
	    return "redirect:/HOD/schedules.html"; 
	}

}
