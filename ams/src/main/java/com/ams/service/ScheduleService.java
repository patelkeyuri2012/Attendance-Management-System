package com.ams.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ams.entity.Schedule;
import com.ams.repository.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule(String schedule, String details, int day, int month, int year, LocalTime startTime, LocalTime endTime, String createdBy, String modifiedBy) {
        Schedule schedules = new Schedule();
        schedules.setSchedule(schedule);
        schedules.setDetails(details);
        schedules.setDay(day);
        schedules.setMonth(month);
        schedules.setYear(year);
        schedules.setStartTime(startTime);
        schedules.setEndTime(endTime);
        schedules.setCreatedon(new Date());
        schedules.setCreatedBy(createdBy);
        schedules.setUpdatedon(new Date());
        schedules.setModifiedBy(modifiedBy);
        return scheduleRepository.save(schedules);
    }

    public Schedule updateSchedule(int scheduleId, String scheduleName, String details, int day, int month, int year, LocalTime startTime, LocalTime endTime, String modifiedBy) {
        Schedule schedules = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule Id:" + scheduleId));

        schedules.setSchedule(scheduleName);
        schedules.setDetails(details);
        schedules.setDay(day);
        schedules.setMonth(month);
        schedules.setYear(year);
        schedules.setStartTime(startTime);
        schedules.setEndTime(endTime);
        schedules.setUpdatedon(new Date());
        schedules.setModifiedBy(modifiedBy);

        return scheduleRepository.save(schedules);
    }

    public void deleteSchedule(int scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
 
    public Schedule getScheduleById(int scheduleId) {
        return scheduleRepository.findByScheduleid(scheduleId);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}
}
