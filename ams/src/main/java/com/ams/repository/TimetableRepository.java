package com.ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Teacher;
import com.ams.entity.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

	Timetable findByTimetableid(int timetableId);

	List<Timetable> findBySemester(Semester semester);

	List<Timetable> findByProgramAndSemester(Program program, Semester semester);

	List<Timetable> findByTeacher(Teacher teacher);

	@Query("SELECT t FROM Timetable t WHERE t.teacher = :teacher AND t.day = :day")
	List<Timetable> findByTeacherAndDay(@Param("teacher") Teacher teacher, @Param("day") String day);

	List<Timetable> findByDay(String currentDay);

	@Query("SELECT t FROM Timetable t WHERE t.teacher = :teacher")
	List<Timetable> findByTeacher_Teacherid(@Param("teacher") Optional<Teacher> teacher);

}
