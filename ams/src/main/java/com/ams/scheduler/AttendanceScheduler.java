package com.ams.scheduler;

import com.ams.entity.Attendance;
import com.ams.entity.AttendanceStatus;
import com.ams.entity.TakeAttendance;
import com.ams.entity.TakeAttendanceStatus;
import com.ams.entity.StudentLeave;
import com.ams.repository.AttendanceMasterRepository;
import com.ams.repository.AttendanceRepository;
import com.ams.repository.TakeAttendanceRepository;
import com.ams.service.AttendanceStatusService;
import com.ams.service.StudentLeaveService;
import com.ams.service.TakeAttendanceStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class AttendanceScheduler {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private StudentLeaveService studentLeaveService;

	@Autowired
	private AttendanceStatusService attendanceStatusService;
	
	@Autowired
	private TakeAttendanceStatusService takeAttendanceStatusService;

	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;
	
	@Autowired
	private TakeAttendanceRepository takeAttendanceRepository;

	@Scheduled(cron = "0 * * * * ?")
	public void updateLeaveStatus() {
		LocalDate today = LocalDate.now();
		java.sql.Date todaySqlDate = java.sql.Date.valueOf(today);

		List<Object[]> attendanceResults = attendanceMasterRepository.findAttendanceByDate(todaySqlDate);

		for (Object[] result : attendanceResults) {
			Attendance attendance = (Attendance) result[1];
			StudentLeave leave = studentLeaveService.getLeaveByStudentAndDate(attendance.getStudent(), todaySqlDate);

			if (leave != null) {
				AttendanceStatus leaveStatus = attendanceStatusService.getAttendanceStatusById(3);
				attendance.setStatus(leaveStatus);
				attendance.setLeave(leave);
				attendanceRepository.save(attendance);
				System.out.println("AttendanceStatus is updated for ID: " + attendance.getAttendanceid());
			}
		}
	}
	
	@Scheduled(cron = "0 * * * * ?")
	public void updateAttendanceStatus() {
	    LocalTime now = LocalTime.now();

	    List<TakeAttendance> takeAttendanceList = takeAttendanceRepository.findAll();

	    for (TakeAttendance takeAttendance : takeAttendanceList) {
	        try {
	            LocalTime attendanceStartTime = takeAttendance.getAttendanceMasterid().getTimetable().getStartTime();
	            LocalTime attendanceEndTime = takeAttendance.getAttendanceMasterid().getTimetable().getEndTime();

	            TakeAttendanceStatus updatedStatus;

	            if (attendanceEndTime.isBefore(now) && takeAttendance.getTakeattendancestatus().getTakeAttendanceStatusid() != 1) {
	                updatedStatus = takeAttendanceStatusService.getTakeAttendanceStatusById(3);
	            } else if (attendanceStartTime.isAfter(now) && takeAttendance.getTakeattendancestatus().getTakeAttendanceStatusid() != 1) {
	                updatedStatus = takeAttendanceStatusService.getTakeAttendanceStatusById(4);
	            } else if (!now.isBefore(attendanceStartTime) && !now.isAfter(attendanceEndTime) && takeAttendance.getTakeattendancestatus().getTakeAttendanceStatusid() != 1) {
	                updatedStatus = takeAttendanceStatusService.getTakeAttendanceStatusById(2);
	            } else {
	                continue;
	            }

	            takeAttendance.setTakeattendancestatus(updatedStatus);
	            takeAttendanceRepository.save(takeAttendance);
	            System.out.println("TakeAttendanceStatus updated for ID: " + takeAttendance.getTakeattendanceid());
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error updating attendance status for ID: " + takeAttendance.getTakeattendanceid());
	        }
	    }
	}

}
