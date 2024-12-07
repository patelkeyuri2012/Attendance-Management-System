package com.ams.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Attendance;
import com.ams.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> findByStudentId(int studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
    
    public List<Map<String, Object>> getAttendanceData(int studentId) {
        List<Object[]> results = attendanceRepository.findAttendanceDetailsByStudentId(studentId);

        List<Map<String, Object>> attendanceList = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> attendanceData = new HashMap<>();
            attendanceData.put("subjectCode", row[0]);
            attendanceData.put("subjectName", row[1]);
            attendanceData.put("totalLectures", row[2]);
            attendanceData.put("attendedLectures", row[3]);
            attendanceData.put("percentage", row[4]); 

            attendanceList.add(attendanceData);
        }

        return attendanceList;
    }

    public List<Object[]> getAttendanceDetails(int studentId, String subjectName) {
        return attendanceRepository.findAttendanceByStudentAndSubject(studentId, subjectName);
    }


}
