package com.hernaval.judomanager.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hernaval.judomanager.model.Schedule;
import com.hernaval.judomanager.repository.ScheduleRepository;
import com.hernaval.judomanager.service.impl.TrainingSessionServiceImpl;

@Testable
@ExtendWith(MockitoExtension.class)
public class TrainingSessionAttendanceTest {
	
	@InjectMocks
	TrainingSessionServiceImpl service;
	
	@Mock 
	ScheduleRepository scheduleRepository;
	
	@BeforeEach
	public void setUp() {
	}
	
	
	public  Schedule buildSchedule(LocalDate date) {
		return Schedule.builder()
				.startDate(date)
				.build();
	}
}
