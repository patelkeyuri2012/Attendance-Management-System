package com.ams.repository;

import com.ams.entity.Student;
import com.ams.entity.StudentLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Integer> {

	List<StudentLeave> findByLeaveType_LeaveTypeid(int leaveTypeId);

	List<StudentLeave> findByLeaveBy(Student leaveBy);

	@Query("SELECT sl FROM StudentLeave sl WHERE sl.leaveBy.id = :studentId AND :checkDate BETWEEN sl.startDate AND sl.endDate AND sl.leaveStatus.id = 1")
	StudentLeave findByStudentAndDate(@Param("studentId") int studentId, @Param("checkDate") Date checkDate);

	}
