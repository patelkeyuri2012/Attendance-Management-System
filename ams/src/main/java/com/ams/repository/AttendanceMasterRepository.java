package com.ams.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.entity.AttendanceMaster;
import com.ams.entity.TakeAttendance;

public interface AttendanceMasterRepository extends JpaRepository<AttendanceMaster, Integer> {

	@Query("SELECT am, att FROM AttendanceMaster am, Attendance att WHERE am.date = :date AND att.attendancemaster.attendancemasterid = am.attendancemasterid")
	List<Object[]> findAttendanceByDate(@Param("date") Date date);

	@Query("SELECT a FROM AttendanceMaster a WHERE a.program.programid = :programId AND a.semester.semesterid = :semesterId AND a.subject.subjectid = :subjectId AND a.date = :lectureDate")
		Optional<AttendanceMaster> findAttendanceMaster(
		        @Param("programId") int programId,
		        @Param("semesterId") int semesterId,
		        @Param("subjectId") int subjectId,
		        @Param("lectureDate") Date lectureDate);

	@Query("SELECT a FROM AttendanceMaster a WHERE a.timetable.id = :timetableId")
	List<AttendanceMaster> findByTimetableId(@Param("timetableId") int timetableId);

	@Query("SELECT t FROM TakeAttendance t WHERE t.attendancemasterid.id = :attendanceMasterId")
	Optional<TakeAttendance> findByAttendanceMasterId(@Param("attendanceMasterId") int attendanceMasterId);


}
