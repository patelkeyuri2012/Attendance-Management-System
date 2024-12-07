package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.AttendanceStatus;
import com.ams.repository.AttendanceStatusRepository;

@Service
public class AttendanceStatusService {

	@Autowired
	private AttendanceStatusRepository attendanceStatusRepository;

	public void createAttendanceStatus(String attendanceStatusName) {
		AttendanceStatus attendanceStatus = new AttendanceStatus();
		attendanceStatus.setAttendanceStatus(attendanceStatusName);
		attendanceStatusRepository.save(attendanceStatus);
	}
	
	public AttendanceStatus getAttendanceStatusById(int attendanceStatusId) {
		return attendanceStatusRepository.findByAttendanceStatusId(attendanceStatusId);
	}
	
	 public List<AttendanceStatus> findAll() {
	        return attendanceStatusRepository.findAll();
	    }

	public List<AttendanceStatus> getAttendanceStatusByIds(List<Integer> attendanceStatusIds) {
		return attendanceStatusRepository.findAllById(attendanceStatusIds);
	}

}