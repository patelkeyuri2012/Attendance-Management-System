package com.ams.repository;

import com.ams.entity.AttendanceMaster;
import com.ams.entity.TakeAttendance;
import com.ams.entity.Teacher;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TakeAttendanceRepository extends JpaRepository<TakeAttendance, Integer> {

	List<TakeAttendance> findByAttendanceon(Date todaySqlDate);

	@Query("SELECT am FROM TakeAttendance am WHERE am.attendanceby = :teacherId AND am.attendanceon = :date")
	List<TakeAttendance> findByTeacherIdAndDate(@Param("teacherId") Teacher teacher, @Param("date") java.sql.Date date);

	@Query("SELECT ta FROM TakeAttendance ta JOIN ta.attendancemasterid am WHERE ta.attendanceby = :teacher AND ta.attendanceon BETWEEN :startDate AND :endDate AND ta.takeattendancestatus.id IN (2, 3, 4)")
	List<TakeAttendance> findAttendanceWithinDateRangeAndStatus(@Param("teacher") Teacher teacher,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	Optional<TakeAttendance> findBytakeattendanceid(@Param("attendanceId") int attendanceId);

	TakeAttendance findBytakeattendancestatus(AttendanceMaster attendanceMaster);

	@Query("SELECT ta FROM TakeAttendance ta WHERE ta.attendanceon = :date AND ta.takeattendancestatus.id = :statusId")
	List<TakeAttendance> findByAttendanceOnAndStatus(@Param("date") Date date, @Param("statusId") int statusId);

	@Query("SELECT ta FROM TakeAttendance ta JOIN ta.attendancemasterid am WHERE ta.attendanceby = :teacher AND ta.attendanceon BETWEEN :startDate AND :endDate AND ta.takeattendancestatus.id IN (1)")
	List<TakeAttendance> findAttendanceByDate(@Param("teacher") Teacher teacher, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT t FROM TakeAttendance t WHERE t.attendancemasterid.attendancemasterid = :attendancemasterid")
	TakeAttendance findByAttendanceMasterId(@Param("attendancemasterid") int attendancemasterid);

	List<TakeAttendance> findByattendancemasterid(AttendanceMaster attendanceMaster);

	@Query(value = "SELECT ta.takeattendanceid AS takeAttendanceId, ta.attendancemasterid AS attendanceMasterId, ta.takeattendancestatusid AS takeAttendanceStatusId, ta.attendanceon AS attendanceOn, ta.attendanceby AS attendanceBy FROM takeattendance ta JOIN attendancemaster am ON ta.attendancemasterid = am.attendancemasterid JOIN timetable t ON am.timetableid = t.timetableid WHERE t.timetableid = :timetableId AND ta.attendanceon = :attendanceDate", nativeQuery = true)
	List<TakeAttendance> findByTimetableAndAttendanceOn(@Param("timetableId") int timetableId,
			@Param("attendanceDate") Date attendanceDate);

	@Query("SELECT COUNT(ta) FROM TakeAttendance ta WHERE ta.attendanceby = :teacher " +
		       "AND ta.takeattendancestatus.id = :statusId " +
		       "AND ta.attendanceon = :today")
		int countByTakeAttendanceStatusAndDate(Teacher teacher, int statusId, Date today);


}
