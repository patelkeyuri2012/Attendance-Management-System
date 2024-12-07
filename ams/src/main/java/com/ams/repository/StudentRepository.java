package com.ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ams.entity.Program;
import com.ams.entity.Student;
import com.ams.entity.Subject;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByStudentid(int studentId);

	Student findBygvpemail(String email);

	@Query("SELECT s FROM Student s WHERE s.department.id = :departmentId AND s.active = true")
	List<Student> findByDepartmentId(@Param("departmentId") Integer departmentId);

	@Query("SELECT s FROM Student s WHERE s.department.department = :departmentName AND s.active = true")
	List<Student> findByDepartmentName(@Param("departmentName") String departmentName);

	@Query("SELECT COUNT(s) FROM Student s WHERE s.program.id = :programId AND s.semester.id = :semesterId")
	int countByProgramAndSemester(@Param("programId") int programId, @Param("semesterId") int semesterId);

	@Query("SELECT COUNT(s) FROM Student s WHERE s.program.id = :programId AND s.semester.id = :semesterId AND s.active = false")
	int countByProgramAndSemesterByDeactive(@Param("programId") int programId, @Param("semesterId") int semesterId);

	@Query("SELECT COUNT(s) FROM Student s WHERE s.program.id = :programId AND s.semester.id = :semesterId AND s.active = true")
	int countByProgramAndSemesterByActive(@Param("programId") int programId, @Param("semesterId") int semesterId);

	List<Student> findByProgramProgramid(int programId);

	@Query("SELECT s FROM Student s WHERE s.program.id = :programId AND s.semester.id = :semesterId AND EXISTS (SELECT 1 FROM Subject sub WHERE sub.subjectid = :subjectId AND sub.program.id = s.program.id AND sub.semester.id = s.semester.id)")
	List<Student> findStudentsByProgramSemesterAndSubject(@Param("programId") int programId,
			@Param("semesterId") int semesterId, @Param("subjectId") int subjectId);
	
	@Query("SELECT s FROM Student s JOIN s.subjects sub WHERE sub.subjectid = :subjectId")
	List<Student> findStudentsBySubject(@Param("subjectId") int subjectId);

	List<Student> findByProgram(Program program);

	List<Student> findBysubjects(Subject subject);

}
