package com.ams.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Attendance;
import com.ams.entity.AttendanceMaster;
import com.ams.entity.AttendanceStatus;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Student;
import com.ams.entity.StudentLeave;
import com.ams.entity.Subject;
import com.ams.entity.TakeAttendance;
import com.ams.entity.TakeAttendanceStatus;
import com.ams.entity.Teacher;
import com.ams.entity.Timetable;
import com.ams.repository.AttendanceMasterRepository;
import com.ams.repository.AttendanceRepository;
import com.ams.repository.ProgramRepository;
import com.ams.repository.SemesterRepository;
import com.ams.repository.TakeAttendanceRepository;
import com.ams.repository.TeacherRepository;
import com.ams.repository.TimetableRepository;

@Service
public class TimetableService {

	@Autowired
	private TimetableRepository timetableRepository;

	@Autowired
	private SemesterRepository semesterRepository;

	@Autowired
	private ProgramRepository programRepository;

	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentLeaveService studentLeaveService;

	@Autowired
	private AttendanceStatusService attendanceStatusService;
	
	@Autowired
	private  TeacherRepository teacherRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private TakeAttendanceRepository takeAttendanceRepository;

	@Autowired
	private TakeAttendanceStatusService takeAttendanceStatusService;

	public Timetable createTimetable(Subject subject, Program program, Semester semester, String day, Teacher teacher,
			LocalTime startTime, LocalTime endTime, String createdBy, String modifiedBy) {
		Timetable timetables = new Timetable();
		timetables.setSubject(subject);
		timetables.setDay(day);
		timetables.setTeacher(teacher);
		timetables.setProgram(program);
		timetables.setSemester(semester);
		timetables.setStartTime(startTime);
		timetables.setEndTime(endTime);
		timetables.setCreatedon(new Date());
		timetables.setCreatedBy(createdBy);
		timetables.setUpdatedon(new Date());
		timetables.setModifiedBy(modifiedBy);

		timetables = timetableRepository.save(timetables);

		int semesterNumber = semester.getSemesterid();

		LocalDate semesterStartDate = null;
		LocalDate semesterEndDate = null;

		if (semesterNumber % 2 == 0) {
			semesterStartDate = LocalDate.of(2024, 1, 1);
			semesterEndDate = LocalDate.of(2024, 6, 30);
		} else {
			semesterStartDate = LocalDate.of(2024, 7, 1);
			semesterEndDate = LocalDate.of(2024, 12, 31);
		}

		DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());

		List<LocalDate> specificWeekdays = new ArrayList<>();

		LocalDate current = semesterStartDate;

		while (current.getDayOfWeek() != dayOfWeek) {
			current = current.plusDays(1);
		}

		while (!current.isAfter(semesterEndDate)) {
			specificWeekdays.add(current);
			current = current.plusWeeks(1);
		}

		for (LocalDate specificDate : specificWeekdays) {
			AttendanceMaster attendanceMaster = new AttendanceMaster();
			attendanceMaster.setDate(Date.from(specificDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			attendanceMaster.setProgram(program);
			attendanceMaster.setSemester(semester);
			attendanceMaster.setDepartment(program.getDepartment());
			attendanceMaster.setSubject(subject);
			attendanceMaster.setTeacher(teacher);
			attendanceMaster.setTimetable(timetables);

			attendanceMaster = attendanceMasterRepository.save(attendanceMaster);

			TakeAttendance takeAttendance = new TakeAttendance();

			takeAttendance.setAttendancemasterid(attendanceMaster);
			takeAttendance.setAttendanceon(attendanceMaster.getDate());
			takeAttendance.setAttendanceBy(attendanceMaster.getTeacher());

			TakeAttendanceStatus takeattendancePendingStatus = takeAttendanceStatusService
					.getTakeAttendanceStatusById(4);
			takeAttendance.setTakeattendancestatus(takeattendancePendingStatus);

			takeAttendance = takeAttendanceRepository.save(takeAttendance);

			List<Student> students = studentService.findStudentsBySubject(subject.getSubjectid());
			for (Student student : students) {
				Attendance attendance = new Attendance();
				attendance.setAttendancemaster(attendanceMaster);
				attendance.setStudent(student);
				attendance.setTeacher(teacher);

				StudentLeave leave = studentLeaveService.getLeaveByStudentAndDate(student, attendanceMaster.getDate());
				if (leave != null) {
					AttendanceStatus leaveStatus = attendanceStatusService.getAttendanceStatusById(3);
					attendance.setStatus(leaveStatus);
					attendance.setLeave(leave);
				} else {
					AttendanceStatus pendingStatus = attendanceStatusService.getAttendanceStatusById(4);
					attendance.setStatus(pendingStatus);
				}
				attendanceRepository.save(attendance);
			}
		}

		return timetables;
	}

	public Timetable updateTimetable(int timetableId, Subject subject, Program program, Semester semester, String day,
			Teacher teacher, LocalTime startTime, LocalTime endTime, String modifiedBy) {

		Timetable timetable = timetableRepository.findById(timetableId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid timetable ID: " + timetableId));

		timetable.setSubject(subject);
		timetable.setDay(day);
		timetable.setTeacher(teacher);
		timetable.setProgram(program);
		timetable.setSemester(semester);
		timetable.setStartTime(startTime);
		timetable.setEndTime(endTime);
		timetable.setUpdatedon(new Date());
		timetable.setModifiedBy(modifiedBy);

		timetable = timetableRepository.save(timetable);
		System.out.println("Updated Timetable: " + timetable);

		List<AttendanceMaster> attendanceMasters = attendanceMasterRepository.findByTimetableId(timetableId);

		for (AttendanceMaster attendanceMaster : attendanceMasters) {


			attendanceMaster.setProgram(program);
			attendanceMaster.setSemester(semester);
			attendanceMaster.setDepartment(program.getDepartment());
			attendanceMaster.setSubject(subject);
			attendanceMaster.setTeacher(teacher);

			attendanceMasterRepository.save(attendanceMaster); 

			List<Student> currentStudents = studentService.findStudentsBySubject(subject.getSubjectid());
			List<Integer> currentStudentIds = currentStudents.stream().map(Student::getStudentid)
					.collect(Collectors.toList());

			List<Attendance> existingAttendanceRecords = attendanceRepository
					.findByAttendancemaster_Attendancemasterid(attendanceMaster.getAttendancemasterid());
			List<Integer> existingStudentIds = existingAttendanceRecords.stream()
					.map(att -> att.getStudent().getStudentid()).collect(Collectors.toList());

			List<Integer> studentsToRemove = new ArrayList<>(existingStudentIds);
			studentsToRemove.removeAll(currentStudentIds);

			List<Integer> studentsToAdd = new ArrayList<>(currentStudentIds);
			studentsToAdd.removeAll(existingStudentIds);


			for (Integer studentIdToRemove : studentsToRemove) {
				Attendance attendanceToRemove = existingAttendanceRecords.stream()
						.filter(att -> att.getStudent() != null && att.getStudent().getStudentid() == studentIdToRemove)
						.findFirst().orElse(null);
				if (attendanceToRemove != null) {
					attendanceRepository.delete(attendanceToRemove);
				}
			}

			for (Integer studentIdToAdd : studentsToAdd) {
				Student studentToAdd = currentStudents.stream()
						.filter(student -> student.getStudentid() == studentIdToAdd).findFirst().orElse(null);

				if (studentToAdd != null) {
					Attendance attendance = new Attendance();
					attendance.setStudent(studentToAdd);
					attendance.setAttendancemaster(attendanceMaster);
					attendance.setTeacher(teacher);

					AttendanceStatus pendingStatus = attendanceStatusService.getAttendanceStatusById(4);
					attendance.setStatus(pendingStatus);

					attendanceRepository.save(attendance);
					System.out.println("Added attendance for student: " + studentToAdd.getStudentid());
				}
			}

			for (Attendance existingAttendance : existingAttendanceRecords) {
				if (currentStudentIds.contains(existingAttendance.getStudent().getStudentid())) {
					existingAttendance.setAttendancemaster(attendanceMaster);
					existingAttendance.setTeacher(teacher);

					StudentLeave leave = studentLeaveService.getLeaveByStudentAndDate(existingAttendance.getStudent(),
							attendanceMaster.getDate());
					if (leave != null) {
						AttendanceStatus leaveStatus = attendanceStatusService.getAttendanceStatusById(3);
						existingAttendance.setStatus(leaveStatus);
						existingAttendance.setLeave(leave);
					} else {
						AttendanceStatus pendingStatus = attendanceStatusService.getAttendanceStatusById(4);
						existingAttendance.setStatus(pendingStatus);
					}

					attendanceRepository.save(existingAttendance);
				}
			}

			List<TakeAttendance> takeAttendances = takeAttendanceRepository.findByattendancemasterid(attendanceMaster);
			for (TakeAttendance takeAttendance : takeAttendances) {
				takeAttendance.setAttendanceMasterid(attendanceMaster);
				takeAttendance.setAttendanceby(teacher);
				takeAttendanceRepository.save(takeAttendance);
			}
		}

		return timetable;
	}

	public void deleteTimetable(int timetableId) {
		timetableRepository.deleteById(timetableId);
	}

	public Timetable getTimetableById(int timetableId) {
		return timetableRepository.findByTimetableid(timetableId);
	}

	public List<Timetable> findAll() {
		return timetableRepository.findAll();
	}

	public List<Timetable> getAllTimetables() {
		return timetableRepository.findAll();
	}

	public List<Timetable> getTimetableBySemesterId(int semesterId) {
		Semester semester = semesterRepository.findById(semesterId)
				.orElseThrow(() -> new RuntimeException("Semester not found"));
		return timetableRepository.findBySemester(semester);
	}

	public List<Timetable> getTimetablesByProgramIdAndSemesterId(int programId, int semesterId) {
		Program program = programRepository.findById(programId)
				.orElseThrow(() -> new RuntimeException("Program not found"));
		Semester semester = semesterRepository.findById(semesterId)
				.orElseThrow(() -> new RuntimeException("Semester not found"));
		return timetableRepository.findByProgramAndSemester(program, semester);
	}

	public List<Timetable> findByTeacherIdAndDay(Teacher teacher, String day) {
		return timetableRepository.findByTeacherAndDay(teacher, day);
	}

	public Date getNextDayOfWeek(Date startDate, String day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		int targetDayOfWeek = getDayOfWeek(day);

		int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int daysToAdd = (targetDayOfWeek - currentDayOfWeek + 7) % 7;

		calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
		return calendar.getTime();
	}

	public int getDayOfWeek(String day) {
		switch (day.toLowerCase()) {
		case "monday":
			return Calendar.MONDAY;
		case "tuesday":
			return Calendar.TUESDAY;
		case "wednesday":
			return Calendar.WEDNESDAY;
		case "thursday":
			return Calendar.THURSDAY;
		case "friday":
			return Calendar.FRIDAY;
		case "saturday":
			return Calendar.SATURDAY;
		case "sunday":
			return Calendar.SUNDAY;
		default:
			return Calendar.MONDAY;
		}
	}

	public List<Timetable> getTimetablesByTeacherId(int teacherId) {
	    Optional<Teacher> teacher = teacherRepository.findById(teacherId);
	    return timetableRepository.findByTeacher_Teacherid(teacher);
	}



}
