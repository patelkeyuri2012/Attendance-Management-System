package com.ams.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Attendance;
import com.ams.entity.AttendanceMaster;
import com.ams.entity.AttendanceStatus;
import com.ams.entity.TakeAttendance;
import com.ams.entity.Student;
import com.ams.entity.TakeAttendanceStatus;
import com.ams.entity.Teacher;
import com.ams.repository.StudentRepository;
import com.ams.repository.AttendanceMasterRepository;
import com.ams.repository.AttendanceRepository;
import com.ams.repository.TakeAttendanceRepository;

@Service
public class TakeAttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private TakeAttendanceStatusService takeAttendanceStatusService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TakeAttendanceRepository takeAttendanceRepository;

	@Autowired
	private AttendanceStatusService attendanceStatusService;

	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;

	public List<TakeAttendance> findByAttendanceOn(Date todaySqlDate) {
		return takeAttendanceRepository.findByAttendanceon(todaySqlDate);
	}

	public List<TakeAttendance> getUpcomingAndPendingAttendance(Teacher teacher, java.sql.Date today) {
	    LocalDate localToday = today.toLocalDate();
	    LocalDate fiveDaysAgo = localToday.minusDays(5);
	    java.sql.Date fiveDaysAgoSqlDate = java.sql.Date.valueOf(fiveDaysAgo);

	    List<TakeAttendance> attendanceList = takeAttendanceRepository.findAttendanceWithinDateRangeAndStatus(
	            teacher,
	            fiveDaysAgoSqlDate,
	            today
	    );

	    return attendanceList;
	}


	public void updateAttendance(int attendanceId, String lectureDate, List<Integer> studentIds,
			String attendanceStatus, Teacher teacher) {

		
		AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(attendanceId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid attendance master ID: " + attendanceId));
		
		AttendanceStatus selectedStatus = determineStatus(attendanceStatus);
		
		AttendanceStatus oppositeStatus = attendanceStatusService
				.getAttendanceStatusById(selectedStatus.getAttendanceStatusid() == 1 ? 2 : 1);
		
		List<Student> allStudents = studentRepository.findBysubjects(attendanceMaster.getSubject());

		Set<Integer> selectedStudentIds = new HashSet<>(studentIds);

		for (Student student : allStudents) {
			boolean isSelected = selectedStudentIds.contains(student.getStudentid());

			AttendanceStatus status = isSelected ? selectedStatus : oppositeStatus;

			Attendance attendance = attendanceRepository
					.findByStudent_StudentidAndAttendancemaster(student.getStudentid(), attendanceMaster)
					.orElseGet(() -> {
						Attendance newAttendance = new Attendance();
						newAttendance.setStudent(student);
						newAttendance.setAttendancemaster(attendanceMaster);
						return newAttendance;
					});

			attendance.setStatus(status);
			attendanceRepository.save(attendance);
			
		}

		TakeAttendance takeAttendance = takeAttendanceRepository.findBytakeattendanceid(attendanceId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid TakeAttendance ID: " + attendanceId));

		TakeAttendanceStatus takeStatus = takeAttendanceStatusService.getTakeAttendanceStatusById(1);
		takeAttendance.setAttendancemasterid(attendanceMaster);
		takeAttendance.setTakeattendancestatus(takeStatus);
		takeAttendance.setAttendanceon(new java.util.Date());
		takeAttendance.setAttendanceby(teacher);

		takeAttendanceRepository.save(takeAttendance);
	}

	private AttendanceStatus determineStatus(String attendanceStatus) {
		if ("present".equalsIgnoreCase(attendanceStatus)) {
			return attendanceStatusService.getAttendanceStatusById(1);
		} else if ("absent".equalsIgnoreCase(attendanceStatus)) {
			return attendanceStatusService.getAttendanceStatusById(2);
		}
		throw new IllegalArgumentException("Invalid attendance status: " + attendanceStatus);
	}

	public List<TakeAttendance> getAttendanceByDate(Teacher teacher, Date sqlStartDate, Date sqlEndDate) {
		 return takeAttendanceRepository.findAttendanceByDate(teacher, sqlStartDate, sqlEndDate);
		 }
	
	public Map<String, Integer> getAttendanceCounts(Teacher teacher) {
	    LocalDate today = LocalDate.now();
	    Date sqlDate = Date.valueOf(today);  

	    Map<String, Integer> counts = new HashMap<>();
	    counts.put("success", takeAttendanceRepository.countByTakeAttendanceStatusAndDate(teacher, 1, sqlDate));
	    counts.put("hold", takeAttendanceRepository.countByTakeAttendanceStatusAndDate(teacher, 2, sqlDate));
	    counts.put("pending", takeAttendanceRepository.countByTakeAttendanceStatusAndDate(teacher, 3, sqlDate));
	    counts.put("upcoming", takeAttendanceRepository.countByTakeAttendanceStatusAndDate(teacher, 4, sqlDate));

	    return counts;
	}

}