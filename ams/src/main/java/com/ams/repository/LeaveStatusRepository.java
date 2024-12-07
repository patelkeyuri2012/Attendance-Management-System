package com.ams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.LeaveStatus;

public interface LeaveStatusRepository extends JpaRepository<LeaveStatus, Integer> {
    LeaveStatus findByLeaveStatusid(int leaveStatusid);
    List<LeaveStatus> findAll();
}