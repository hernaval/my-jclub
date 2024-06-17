package com.hernaval.judomanager.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernaval.judomanager.exceptions.NotFoundException;
import com.hernaval.judomanager.model.AttendanceData;
import com.hernaval.judomanager.model.Judoka;
import com.hernaval.judomanager.model.Schedule;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;
import com.hernaval.judomanager.model.TrainingSession;
import com.hernaval.judomanager.model.dto.Attender;
import com.hernaval.judomanager.repository.JudokaRepository;
import com.hernaval.judomanager.repository.ScheduleRepository;
import com.hernaval.judomanager.repository.TrainingSessionRepository;
import com.hernaval.judomanager.service.TrainingSessionService;
import com.hernaval.judomanager.utils.SessionGenerator;

import jakarta.transaction.Transactional;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainingSessionServiceImpl.class);
	
	@Autowired
	private SessionGenerator generator;
	
	@Autowired
	private ScheduleRepository repository;
	
	@Autowired
	private TrainingSessionRepository trainingSessionRepository;
	
	@Autowired
	private JudokaRepository judokaRepository;
	
	@Override
	public List<Session> findTrainingPrograms(ScheduleId scheduleId, LocalDate startDate, LocalDate endDate) {
		Session      start;
		final Session  end = new Session(endDate);
		Schedule  schedule = repository.findById(scheduleId.id()).orElseThrow(() -> new NotFoundException("No schedule "+scheduleId.id()));
		
		if(startDate.isBefore(schedule.getStartDate())) {
			start = new Session(schedule.getStartDate());
		} else {
			start = new Session(startDate);
		}
		
		return generator.generate(start, end);
	}

	@Override
	@Transactional
	public void updateAttendance(List<AttendanceData> data) {
		LOGGER.debug("==> updateAttendance {}", data);
		final List<TrainingSession> trainingSessions = data.stream().map(
				d -> new TrainingSession(d.trainingSessionId(), new Judoka(d.judokaId()), new Schedule(d.scheduleId()), d.date(), d.present())
				).toList();

		trainingSessionRepository.saveAll(trainingSessions);
	}

	@Override
	public List<Attender> findAttenders(ScheduleId scheduleId, LocalDate date) {
		LOGGER.debug("==> findAttenders for scheduleId {} and date {}", scheduleId, date);
		final boolean hasAttendanceData = trainingSessionRepository.existsByDateAndSchedule_Id(date, scheduleId.id());
		List<Attender> attenders  = new ArrayList<>(); 
		if(hasAttendanceData) {
			LOGGER.info("<== Attendance data already exists, return data");
			final List<TrainingSession> attendances = trainingSessionRepository.findByDateAndSchedule_Id(date, scheduleId.id());
			attenders   = attendances.stream()
					.map(a -> new Attender(a.getId(), a.getJudoka().getFirstname(), a.getJudoka().getLastname(), a.getDate(), a.isPresent())).toList();
		}
		else {
			LOGGER.info("<== There is no attendance data yet, return all judoka");
			final List<Judoka> judokas   = judokaRepository.findAll();
			attenders = judokas.stream().map(j -> new Attender(null, j.getFirstname(), j.getLastname(), null, null)).toList();
		}
		
		return attenders;
	}
}
