package com.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ams.entity.Registration;
import com.ams.entity.User;
import com.ams.entity.UserRole;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	
    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admin a WHERE a.email = :email")
    boolean isAdmin(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Teacher t WHERE t.email = :email")
    boolean isTeacher(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.email = :email")
    boolean isAdmission(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    void updatePasswordByEmail(String email, String newPassword);

    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.password = ?2 WHERE a.email = ?1")
    void updateAdminPasswordByEmail(String email, String newPassword);

    @Modifying
    @Transactional
    @Query("UPDATE Teacher r SET r.password = ?2 WHERE r.email = ?1")
    void updateTeacherPasswordByEmail(String email, String newPassword);

    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.password = ?2 WHERE s.email = ?1")
    void updateStudentPasswordByEmail(String email, String newPassword);

	Optional<User> findByUserid(int id);

	Optional<UserRole> findByUserid(User userId);
	
	Optional<Registration> findTeacherDetailsByEmail(String email);

	Optional<Registration> findStudentDetailsByEmail(String email);

	Optional<Registration> findAdminDetailsByEmail(String email); 
}
