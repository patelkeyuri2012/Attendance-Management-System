package com.ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ams.entity.Teacher;
import com.ams.service.TakeAttendanceService;

@Controller
public class AttendanceController {

    @Autowired
    private TakeAttendanceService takeAttendanceService;

    @PostMapping("/saveHODAttendance")
    public String saveHODAttendance(@RequestParam("attendanceid") int attendanceId,
                                  @RequestParam("lectureDate") String lectureDate,
                                  @RequestParam("studentIds[]") List<Integer> studentIds,
                                  @RequestParam("attendanceStatus") String attendanceStatus,
                                  @RequestParam("attendanceBy") Teacher teacher) {
        try {
            takeAttendanceService.updateAttendance(attendanceId, lectureDate, studentIds, attendanceStatus, teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/HOD/takeAttendance.html";
        }
        return "redirect:/HOD/takeAttendance.html";
    }
    
    @PostMapping("/saveTeacherAttendance")
    public String saveTeacherAttendance(@RequestParam("attendanceid") int attendanceId,
                                  @RequestParam("lectureDate") String lectureDate,
                                  @RequestParam("studentIds[]") List<Integer> studentIds,
                                  @RequestParam("attendanceStatus") String attendanceStatus,
                                  @RequestParam("attendanceBy") Teacher teacher) {
        try {
            takeAttendanceService.updateAttendance(attendanceId, lectureDate, studentIds, attendanceStatus, teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/teacher/takeAttendance.html";
        }
        return "redirect:/teacher/takeAttendance.html";
    }
    
    @PostMapping("/updateHODAttendance")
    public String updateHODAttendance(@RequestParam("attendanceid") int attendanceId,
                                      @RequestParam("lectureDate") String lectureDate,
                                      @RequestParam("studentIds[]") List<Integer> studentIds,
                                      @RequestParam("attendanceStatus") String attendanceStatus,
                                      @RequestParam("attendanceBy") Teacher teacher) {
       
        try {
            takeAttendanceService.updateAttendance(attendanceId, lectureDate, studentIds, attendanceStatus, teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/HOD/viewAttendance.html";
        }
        return "redirect:/HOD/viewAttendance.html";
    }

    @PostMapping("/updateTeacherAttendance")
    public String updateTeacherAttendance(@RequestParam("attendanceid") int attendanceId,
                                  @RequestParam("lectureDate") String lectureDate,
                                  @RequestParam("studentIds[]") List<Integer> studentIds,
                                  @RequestParam("attendanceStatus") String attendanceStatus,
                                  @RequestParam("attendanceBy") Teacher teacher) {
        try {
            takeAttendanceService.updateAttendance(attendanceId, lectureDate, studentIds, attendanceStatus, teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/teacher/viewAttendance.html";
        }
        return "redirect:/teacher/viewAttendance.html";
    }
}

