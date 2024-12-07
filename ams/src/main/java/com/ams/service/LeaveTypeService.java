package com.ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.LeaveType;
import com.ams.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {

	@Autowired
	private LeaveTypeRepository leaveTypeRepository;

	public void createLeaveType(String leaveTypeName) {
		LeaveType leaveType = new LeaveType();
		leaveType.setLeaveType(leaveTypeName);
		leaveTypeRepository.save(leaveType);
	}
	
	public LeaveType getLeaveTypeById(int leaveTypeId) {
		return leaveTypeRepository.findByLeaveTypeid(leaveTypeId);
	}
	
	 public List<LeaveType> findAll() {
	        return leaveTypeRepository.findAll();
	    }

	public List<LeaveType> getLeaveTypeByIds(List<Integer> leaveTypeIds) {
		return leaveTypeRepository.findAllById(leaveTypeIds);
	}

	public List<LeaveType> findAllByIsActive(boolean active) {
		 return leaveTypeRepository.findAllByActive(active);
	}
}