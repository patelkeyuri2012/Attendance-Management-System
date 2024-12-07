package com.ams.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.TakeAttendanceStatus;
import com.ams.repository.TakeAttendanceStatusRepository;

@Service
public class TakeAttendanceStatusService {

	@Autowired
	private TakeAttendanceStatusRepository takeAttendanceStatusRepository;

	public void createTakeAttendanceStatus(String takeAttendanceStatusName) {
		TakeAttendanceStatus takeAttendanceStatus = new TakeAttendanceStatus();
		takeAttendanceStatus.setTakeAttendanceStatus(takeAttendanceStatusName);
		takeAttendanceStatusRepository.save(takeAttendanceStatus);
	}
	
	public TakeAttendanceStatus getTakeAttendanceStatusById(int takeAttendanceStatusId) {
		return takeAttendanceStatusRepository.findByTakeAttendanceStatusid(takeAttendanceStatusId);
	}
	
	 public List<TakeAttendanceStatus> findAll() {
	        return takeAttendanceStatusRepository.findAll();
	    }

	public List<TakeAttendanceStatus> getTakeAttendanceStatusByIds(List<Integer> takeAttendanceStatusIds) {
		return takeAttendanceStatusRepository.findAllById(takeAttendanceStatusIds);
	}

	public TakeAttendanceStatus findByAttendanceOnAndStatus(Date todaySqlDate, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}