package com.ams.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ams.entity.TakeAttendanceStatus;

public interface TakeAttendanceStatusRepository extends JpaRepository<TakeAttendanceStatus, Integer> {
    TakeAttendanceStatus findByTakeAttendanceStatusid(int takeAttendanceStatusid);
    List<TakeAttendanceStatus> findAll();
}	