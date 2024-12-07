package com.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ams.entity.LeaveStatus;
import com.ams.entity.LeaveType;
import com.ams.entity.Student;
import com.ams.entity.Teacher;
import com.ams.service.TeacherLeaveService;
import com.ams.service.LeaveStatusService;
import com.ams.service.StudentLeaveService;

import java.util.Date;

@Controller
public class LeaveController {

    @Autowired
    private TeacherLeaveService teacherLeaveService;
    
    @Autowired
    private StudentLeaveService studentLeaveService;
    
    @Autowired
    private LeaveStatusService leaveStatusService;

    @PostMapping("/createHODLeave")
    public String createHODLeave(
            @RequestParam("leaveStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveStartDate,
            @RequestParam("leaveEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveEndDate,
            @RequestParam("leaveType") LeaveType leaveType,
            @RequestParam("leaveReason") String leaveReason,
            @RequestParam(value = "supportingDocument", required = false) MultipartFile supportingDocument,
            @RequestParam(value = "halfday", defaultValue = "false") boolean halfDay,
            @RequestParam(value = "leaveBy", required = false) Teacher leaveBy,
            RedirectAttributes redirectAttributes
    ) {
        String documentPath = teacherLeaveService.saveDocument(supportingDocument);
        
        LeaveStatus pendingStatus = leaveStatusService.getLeaveStatusById(2); 
        
        teacherLeaveService.createLeave(leaveStartDate, leaveEndDate, leaveType, leaveReason, documentPath, halfDay, leaveBy, pendingStatus);

        redirectAttributes.addFlashAttribute("successMessage", "Leave request submitted successfully!");
        return "redirect:/HOD/takeLeave.html";
    }
    

    @PostMapping("/createTeacherLeave")
    public String createTeacherLeave(
            @RequestParam("leaveStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveStartDate,
            @RequestParam("leaveEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveEndDate,
            @RequestParam("leaveType") LeaveType leaveType,
            @RequestParam("leaveReason") String leaveReason,
            @RequestParam(value = "supportingDocument", required = false) MultipartFile supportingDocument,
            @RequestParam(value = "halfday", defaultValue = "false") boolean halfDay,
            @RequestParam(value = "leaveBy", required = false) Teacher leaveBy,
            RedirectAttributes redirectAttributes
    ) {
        String documentPath = teacherLeaveService.saveDocument(supportingDocument);
        
        LeaveStatus pendingStatus = leaveStatusService.getLeaveStatusById(2); 
        
        teacherLeaveService.createLeave(leaveStartDate, leaveEndDate, leaveType, leaveReason, documentPath, halfDay, leaveBy, pendingStatus);

        redirectAttributes.addFlashAttribute("successMessage", "Leave request submitted successfully!");
        return "redirect:/teacher/leave.html";
    }
    
    @PostMapping("/createStudentLeave")
    public String createStudentLeave(
            @RequestParam("leaveStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveStartDate,
            @RequestParam("leaveEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date leaveEndDate,
            @RequestParam("leaveType") LeaveType leaveType,
            @RequestParam("leaveReason") String leaveReason,
            @RequestParam(value = "supportingDocument", required = false) MultipartFile supportingDocument,
            @RequestParam(value = "leaveBy", required = false) Student leaveBy,
            RedirectAttributes redirectAttributes
    ) {
        String documentPath = studentLeaveService.saveDocument(supportingDocument);
        
        LeaveStatus pendingStatus = leaveStatusService.getLeaveStatusById(2); 
        
        studentLeaveService.createLeave(leaveStartDate, leaveEndDate, leaveType, leaveReason, documentPath, leaveBy, pendingStatus);

        redirectAttributes.addFlashAttribute("successMessage", "Leave request submitted successfully!");
        return "redirect:/student/leave.html";
    }
    
    @PostMapping("/approveTeacherLeave")
    public String approveTeacherLeave(@RequestParam("leaveid") Integer leaveId) {
        LeaveStatus approvalStatus = leaveStatusService.getLeaveStatusById(1); 
        teacherLeaveService.approveLeave(leaveId, approvalStatus);
        return "redirect:/admin/leave.html"; 
    }

    @PostMapping("/rejectTeacherLeave")
    public String rejectTeacherLeave(@RequestParam("leaveid") Integer leaveId) {
    	LeaveStatus rejectStatus = leaveStatusService.getLeaveStatusById(3);
        teacherLeaveService.rejectLeave(leaveId, rejectStatus);
        return "redirect:/admin/leave.html";
    }
    
    @PostMapping("/approveStudentLeave")
    public String approveStudentLeave(@RequestParam("leaveid") Integer leaveId) {
        LeaveStatus approvalStatus = leaveStatusService.getLeaveStatusById(1); 
        studentLeaveService.approveLeave(leaveId, approvalStatus);
        return "redirect:/HOD/viewLeave.html"; 
    }

    @PostMapping("/rejectStudentLeave")
    public String rejectStudentLeave(@RequestParam("leaveid") Integer leaveId) {
    	LeaveStatus rejectStatus = leaveStatusService.getLeaveStatusById(3);
        studentLeaveService.rejectLeave(leaveId, rejectStatus);
        return "redirect:/HOD/viewLeave.html";
    }
	

}
