package com.ams.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ams.entity.LeaveStatus;
import com.ams.entity.LeaveType;
import com.ams.entity.Student;
import com.ams.entity.StudentLeave;
import com.ams.repository.StudentLeaveRepository;

@Service
public class StudentLeaveService {
	
	@Autowired
	private StudentLeaveRepository studentLeaveRepository;

    public StudentLeave createLeave(Date leaveStartDate, Date leaveEndDate, LeaveType leaveType, String leaveReason,
                             String documentPath, Student leaveBy, LeaveStatus leaveStatus) {
        StudentLeave leave = new StudentLeave();
        leave.setStartDate(leaveStartDate);
        leave.setEndDate(leaveEndDate);
        leave.setLeaveType(leaveType); 
        leave.setReason(leaveReason);
        leave.setSupportingDocumentPath(documentPath);
        leave.setLeaveBy(leaveBy);
        leave.setLeaveStatus(leaveStatus); 

        
        return studentLeaveRepository.save(leave);
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


    public List<StudentLeave> findByStudentId(Student studentId) {
        return studentLeaveRepository.findByLeaveBy(studentId);
    }

	public List<StudentLeave> findAll() {
		return studentLeaveRepository.findAll();
	}

	public void approveLeave(Integer leaveId, LeaveStatus status) {
	    StudentLeave leave = studentLeaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
	    leave.setLeaveStatus(status);
	    studentLeaveRepository.save(leave);
	}
	
	public void rejectLeave(Integer leaveId, LeaveStatus status) {
	    StudentLeave leave = studentLeaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
	    leave.setLeaveStatus(status);
	    studentLeaveRepository.save(leave);
	}

	public StudentLeave getLeaveByStudentAndDate(Student student, Date checkDate) {
	    Integer studentId = student.getStudentid();
	    return studentLeaveRepository.findByStudentAndDate(studentId, checkDate);
	}

	
}
