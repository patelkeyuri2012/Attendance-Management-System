package com.ams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    LeaveType findByLeaveTypeid(int leaveTypeid);
    List<LeaveType> findAll();
	List<LeaveType> findAllByActive(boolean active);

}