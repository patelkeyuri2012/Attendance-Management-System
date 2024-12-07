package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.LeaveStatus;
import com.ams.repository.LeaveStatusRepository;

@Service
public class LeaveStatusService {

	@Autowired
	private LeaveStatusRepository leaveStatusRepository;

	public void createLeaveStatus(String leaveStatusName) {
		LeaveStatus leaveStatus = new LeaveStatus();
		leaveStatus.setLeaveStatus(leaveStatusName);
		leaveStatusRepository.save(leaveStatus);
	}
	
	public LeaveStatus getLeaveStatusById(int leaveStatusId) {
		return leaveStatusRepository.findByLeaveStatusid(leaveStatusId);
	}
	
	 public List<LeaveStatus> findAll() {
	        return leaveStatusRepository.findAll();
	    }

	public List<LeaveStatus> getLeaveStatusByIds(List<Integer> leaveStatusIds) {
		return leaveStatusRepository.findAllById(leaveStatusIds);
	}
}