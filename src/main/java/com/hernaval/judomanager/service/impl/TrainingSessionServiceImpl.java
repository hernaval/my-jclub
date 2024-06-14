package com.hernaval.judomanager.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernaval.judomanager.exceptions.NotFoundException;
import com.hernaval.judomanager.model.AttendanceData;
import com.hernaval.judomanager.model.Judoka;
import com.hernaval.judomanager.model.Schedule;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;
import com.hernaval.judomanager.model.TrainingSession;
import com.hernaval.judomanager.repository.ScheduleRepository;
import com.hernaval.judomanager.repository.TrainingSessionRepository;
import com.hernaval.judomanager.service.TrainingSessionService;
import com.hernaval.judomanager.utils.SessionGenerator;

import jakarta.transaction.Transactional;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService {
	
	@Autowired
	private SessionGenerator generator;
	
	@Autowired
	private ScheduleRepository repository;
	
	@Autowired
	private TrainingSessionRepository trainingSessionRepository;
	
	@Override
	public List<Session> findTrainingPrograms(ScheduleId scheduleId, LocalDate startDate, LocalDate endDate) {
		Session      start;
		final Session  end = new Session(endDate);
		Schedule  schedule = repository.findById(scheduleId.id()).orElseThrow(() -> new NotFoundException("No schedule "+scheduleId.id()));
		
		if(startDate.isBefore(schedule.getStartDate())) {
			start = new Session(schedule.getStartDate());
		} 
		else {
			start = new Session(startDate);
		}
		
		return generator.generate(start, end);
	}

	@Override
	@Transactional
	public void updateAttendance(List<AttendanceData> data) {
		List<TrainingSession> trainingSessions = data.stream().map(
				d -> new TrainingSession(d.trainingSessionId(), new Judoka(d.judokaId()), new Schedule(d.scheduleId()), d.date(), d.present())
				).toList();

		trainingSessionRepository.saveAll(trainingSessions);
	}
	
	
	
	
}
