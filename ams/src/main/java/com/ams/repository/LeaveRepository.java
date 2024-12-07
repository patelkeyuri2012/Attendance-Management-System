package com.ams.repository;

import com.ams.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByLeaveType_LeaveTypeid(int leaveTypeId);

    List<Leave> findByLeaveBy(String leaveBy);
}
