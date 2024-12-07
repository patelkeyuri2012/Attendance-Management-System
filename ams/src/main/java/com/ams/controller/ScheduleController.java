package com.ams.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ams.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping("/addSchedules")
	public String addSchedule(@RequestParam("schedule") String schedule, @RequestParam("details") String details,
			@RequestParam("day") int day, @RequestParam("month") int month, @RequestParam("year") int year,
			@RequestParam("startTime") LocalTime startTime, @RequestParam("endTime") LocalTime endTime,
			@RequestParam(value = "createdBy", required = false) String createdBy,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (createdBy == null) {
			createdBy = "defaultUser";
		}
		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		scheduleService.createSchedule(schedule, details, day, month, year, startTime, endTime, createdBy, modifiedBy);
		return "redirect:/admin/academicCalendar.html";
	}

	@PostMapping("/updateSchedules")
	public String updateSchedule(
			@RequestParam(value = "scheduleid", required = false, defaultValue = "0") int scheduleId,
			@RequestParam("schedule") String schedule, @RequestParam("details") String details,
			@RequestParam(value = "day", required = false, defaultValue = "1") int day,
			@RequestParam(value = "month", required = false, defaultValue = "1") int month,
			@RequestParam(value = "year", required = false, defaultValue = "2020") int year,
			@RequestParam("startTime") LocalTime startTime, @RequestParam("endTime") LocalTime endTime,
			@RequestParam(value = "modifiedBy", required = false) String modifiedBy) {

		if (modifiedBy == null) {
			modifiedBy = "defaultUser";
		}

		scheduleService.updateSchedule(scheduleId, schedule, details, day, month, year, startTime, endTime, modifiedBy);
		return "redirect:/admin/academicCalendar.html";
	}

	@PostMapping("/deleteSchedules")
	public String deleteSchedule(@RequestParam("scheduleid") int scheduleId) {
		scheduleService.deleteSchedule(scheduleId);
		return "redirect:/admin/academicCalendar.html";
	}
}
