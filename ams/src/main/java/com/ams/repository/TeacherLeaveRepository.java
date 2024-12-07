package com.ams.repository;

import com.ams.entity.Teacher;
import com.ams.entity.TeacherLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherLeaveRepository extends JpaRepository<TeacherLeave, Integer> {

    List<TeacherLeave> findByLeaveType_LeaveTypeid(int leaveTypeId);

    List<TeacherLeave> findByLeaveBy(Teacher leaveBy);

    List<TeacherLeave> findByLeaveStatus_LeaveStatus(String leaveStatus);
}
