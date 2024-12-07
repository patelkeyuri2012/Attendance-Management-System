package com.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ams.entity.TeacherLeave;
import com.ams.entity.LeaveStatus;
import com.ams.entity.LeaveType;
import com.ams.entity.Teacher;
import com.ams.repository.TeacherLeaveRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TeacherLeaveService {
	
	@Autowired
	private TeacherLeaveRepository teacherLeaveRepository;

    public TeacherLeave createLeave(Date leaveStartDate, Date leaveEndDate, LeaveType leaveType, String leaveReason,
                             String documentPath, boolean halfDay, Teacher leaveBy, LeaveStatus leaveStatus) {
        TeacherLeave leave = new TeacherLeave();
        leave.setStartDate(leaveStartDate);
        leave.setEndDate(leaveEndDate);
        leave.setLeaveType(leaveType); 
        leave.setReason(leaveReason);
        leave.setSupportingDocumentPath(documentPath);
        leave.setHalfDay(halfDay);
        leave.setLeaveBy(leaveBy);
        leave.setLeaveStatus(leaveStatus); 

        
        return teacherLeaveRepository.save(leave);
    }

    public String saveDocument(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            String uploadDir = "src/main/resources/static/assets/doc/";
            Files.createDirectories(Paths.get(uploadDir));

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "user_" + timeStamp + fileExtension;

            Path filePath = Paths.get(uploadDir + fileName);
            
            Files.write(filePath, file.getBytes());

            return "assets\\doc\\" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<TeacherLeave> findByTeacherId(Teacher teacherId) {
        return teacherLeaveRepository.findByLeaveBy(teacherId);
    }

	public List<TeacherLeave> findAll() {
		return teacherLeaveRepository.findAll();
	}
	
	public List<TeacherLeave> findPendingLeaves() {
	    return teacherLeaveRepository.findByLeaveStatus_LeaveStatus("Pending");
	}


	public void approveLeave(Integer leaveId, LeaveStatus status) {
	    TeacherLeave leave = teacherLeaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
	    leave.setLeaveStatus(status);
	    teacherLeaveRepository.save(leave);
	}
	
	public void rejectLeave(Integer leaveId, LeaveStatus status) {
	    TeacherLeave leave = teacherLeaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
	    leave.setLeaveStatus(status);
	    teacherLeaveRepository.save(leave);
	}

}
