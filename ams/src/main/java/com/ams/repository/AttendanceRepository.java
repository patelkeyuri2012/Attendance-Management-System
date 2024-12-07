package com.ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.entity.Attendance;
import com.ams.entity.AttendanceMaster;
import com.ams.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query("SELECT a FROM Attendance a WHERE a.attendancemaster = :attendanceMaster")
	List<Attendance> findByAttendanceMaster(@Param("attendanceMaster") AttendanceMaster attendanceMaster);

	@Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId AND a.attendancemaster = :attendanceMaster")
	Optional<Attendance> findByStudentAndAttendancemaster(@Param("studentId") Student student,
			@Param("attendanceMaster") AttendanceMaster attendanceMaster);

	Optional<Attendance> findByStudent_StudentidAndAttendancemaster(Integer studentId,
			AttendanceMaster attendanceMaster);

	@Query("SELECT a FROM Attendance a WHERE a.attendancemaster.id = :attendanceMasterId")
	Optional<Attendance> findByAttendanceMasterId(@Param("attendanceMasterId") int attendanceMasterId);

	@Query("SELECT a FROM Attendance a WHERE a.student.studentid = :studentId")
	List<Attendance> findByStudentId(@Param("studentId") int studentId);

	@Query("SELECT DISTINCT s.studentid, s.entrollmentno, s.fname, s.mname, s.lname, a.attendancestatus.id AS attendancestatus FROM Attendance a JOIN a.student s JOIN s.subjects subj JOIN a.attendancemaster am WHERE am.subject.id = :subjectId AND a.attendancemaster.id = :attendanceMasterId AND subj.id = :subjectId")
	List<Object[]> findAttendanceAndSubjectByMasterIdAndSubjectId(@Param("subjectId") int subjectId,
			@Param("attendanceMasterId") int attendanceMasterId);

	@Query("SELECT a FROM Attendance a WHERE a.student.id = :studentId AND a.attendancemaster.id = :attendanceMasterId")
	Optional<Attendance> findAttendanceByStudentAndMaster(@Param("studentId") int studentId,
			@Param("attendanceMasterId") int attendanceMasterId);

	@Query("SELECT a FROM AttendanceMaster a WHERE a.timetable.timetableid = :timetableId")
	Optional<AttendanceMaster> findByTimetableId(@Param("timetableId") int timetableId);

	Optional<Attendance> findByAttendancemaster_AttendancemasteridAndStudent_Studentid(int attendancemasterid,
			int studentid);

	List<Attendance> findByAttendancemaster_Attendancemasterid(int attendancemasterid);

	@Query(value = "SELECT s.subjectcode AS subjectCode, s.subject AS subjectName, COUNT(t.takeattendanceid) AS totalLectures, SUM(CASE WHEN a.attendancestatusid = 1 THEN 1 ELSE 0 END) AS attendedLectures,  ROUND(SUM(CASE WHEN a.attendancestatusid = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(t.takeattendanceid), 2) AS attendancePercentage FROM attendance a JOIN attendancemaster am ON a.attendancemasterid = am.attendancemasterid JOIN takeattendance t ON t.attendancemasterid = am.attendancemasterid JOIN subject s ON am.subjectid = s.subjectid WHERE a.studentid = :studentId AND t.takeattendancestatusid = 1 GROUP BY s.subjectcode, s.subject", nativeQuery = true)
	List<Object[]> findAttendanceDetailsByStudentId(@Param("studentId") int studentId);

	@Query("SELECT am.date, t.attendanceon, t.takeattendancestatus " +
		       "FROM Attendance a " +
		       "JOIN a.attendancemaster am " + 
		       "JOIN TakeAttendance t ON t.attendancemasterid = am " + 
		       "JOIN Subject s ON am.subject = s " + 
		       "WHERE a.student.studentid = :studentId AND s.subject = :subjectName")
		List<Object[]> findAttendanceByStudentAndSubject(@Param("studentId") int studentId, 
		                                                            @Param("subjectName") String subjectName);


}
