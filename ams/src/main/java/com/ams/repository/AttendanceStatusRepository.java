package com.ams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.entity.AttendanceStatus;

public interface AttendanceStatusRepository extends JpaRepository<AttendanceStatus, Integer> {

	@Query("SELECT a FROM AttendanceStatus a WHERE a.attendanceStatusid = :attendanceStatusid")
	AttendanceStatus findByAttendanceStatusId(@Param("attendanceStatusid") int attendanceStatusid);
 
	List<AttendanceStatus> findAll();
}