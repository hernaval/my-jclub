package com.hernaval.judomanager.controller;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hernaval.judomanager.model.AttendanceData;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;
import com.hernaval.judomanager.service.TrainingSessionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Training plan and attendance")
@RestController
@RequestMapping(value = "/sessions")
public class TrainingSessionController {

	@Autowired
	TrainingSessionService service;

	@Operation(description = "Get next training session between a date range for a scheduled activity")
	@GetMapping("/schedules/{id}/sessions")
	public ResponseEntity<List<Session>> getSessionBetween(@PathVariable("id") Long id,
			@DateTimeFormat(iso = DATE) @RequestParam("from") LocalDate from,
			@DateTimeFormat(iso = DATE) @RequestParam("to") LocalDate to) {

		return ResponseEntity.ok().body(service.findTrainingPrograms(new ScheduleId(id), from, to));
	}

	@Operation(description = "Update judoka attendance status in a training session")
	@PostMapping("/attendances")
	public ResponseEntity<?> updateSessionAttendance(@RequestBody List<AttendanceData> data) {
		service.updateAttendance(data);

		return ResponseEntity.status(HttpStatus.CREATED).body("Training attendance updated");
	}
	
	@Operation(description = "Get list of judoka as attenders in a training session")
	@GetMapping("/attendances")
	public ResponseEntity<?> retrieveSessionAttenders(@RequestParam("at") LocalDate at, @RequestParam("scheduleId") Long scheduleId) {
		
		return ResponseEntity.status(HttpStatus.OK).body(service.findAttenders(new ScheduleId(scheduleId), at));
	}

}
