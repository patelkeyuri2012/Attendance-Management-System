package com.ams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ams.entity.Leave;
import com.ams.entity.LeaveStatus;
import com.ams.entity.LeaveType;
import com.ams.repository.LeaveRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LeaveService {
	
	@Autowired
	private LeaveRepository leaveRepository;

    public Leave createLeave(Date leaveStartDate, Date leaveEndDate, LeaveType leaveType, String leaveReason,
                             String documentPath, boolean halfDay, String leaveBy, LeaveStatus leaveStatus) {
        Leave leave = new Leave();
        leave.setStartDate(leaveStartDate);
        leave.setEndDate(leaveEndDate);
        leave.setLeaveType(leaveType); 
        leave.setReason(leaveReason);
        leave.setSupportingDocumentPath(documentPath);
        leave.setHalfDay(halfDay);
        leave.setLeaveBy(leaveBy);
        leave.setLeaveStatus(leaveStatus); 

        
        return leaveRepository.save(leave);
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

            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Leave> findByTeacherId(int teacherId) {
        return leaveRepository.findByLeaveBy(String.valueOf(teacherId));
    }
}
