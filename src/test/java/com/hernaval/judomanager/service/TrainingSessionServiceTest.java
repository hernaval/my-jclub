package com.hernaval.judomanager.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hernaval.judomanager.model.Schedule;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;
import com.hernaval.judomanager.repository.ScheduleRepository;
import com.hernaval.judomanager.service.impl.TrainingSessionServiceImpl;
import com.hernaval.judomanager.utils.SessionGenerator;

@Testable
@ExtendWith(MockitoExtension.class)
public class TrainingSessionServiceTest {
	
	@InjectMocks
	TrainingSessionServiceImpl service;
	
	@Mock 
	SessionGenerator generator;
	
	@Mock 
	ScheduleRepository scheduleRepository;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void should_change_start_date_to_the_nearest() {
		LocalDate start    = LocalDate.of(2024, 05, 25);
		LocalDate end 	   = LocalDate.of(2024, 06, 30);
		ScheduleId id      = new ScheduleId(1L);
		LocalDate expectedStart = LocalDate.of(2024, 06, 01);
		
		when(scheduleRepository.findById(id.id())).thenReturn(Optional.of(buildSchedule(expectedStart)));
		when(generator.generate(new Session(expectedStart), new Session(end))).thenReturn(List.of());
		
		List<Session> sessions = service.findTrainingPrograms(id, start, end);
		
		Assertions.assertEquals(0, sessions.size());
		verify(generator).generate(
				argThat(
						x -> x.getDate().equals(expectedStart) ), argThat(
						y -> true	
								));
	}
	
	@Test
	public void should_start_on_schedule_day() {
		
		Assertions.assertTrue(true);
	}
	
	public  Schedule buildSchedule(LocalDate date) {
		return Schedule.builder()
				.startDate(date)
				.build();
	}
}
