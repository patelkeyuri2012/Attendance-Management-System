package com.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ams.entity.Department;
import com.ams.entity.Program;
import com.ams.entity.Semester;
import com.ams.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	Subject findBySubjectid(int subjectId);

	List<Subject> findAllById(Iterable<Integer> ids);

	List<Subject> findByProgram_ProgramidAndSemester_Semesterid(int programId, int semesterId);

	List<Subject> findAllByDepartment(Department teacherDepartment);

	List<Subject> findByProgramAndSemester(Program program, Semester semester);
	
	@Query("SELECT s FROM Subject s WHERE s.program.programid = :programId AND s.semester.semesterid = :semesterId")
	List<Subject> findByProgramIdAndBySemesterId(int programId, int semesterId);

}