package com.hernaval.judomanager.service;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
		LocalDate start    			= LocalDate.of(2024, 06, 05);
		LocalDate end 	   			= LocalDate.of(2024, 06, 30);
		ScheduleId id      			= new ScheduleId(1L);
		LocalDate scheduleStartDate = LocalDate.of(2024, 06, 04);
		LocalDate expectedStart 	= LocalDate.of(2024, 06, 11);
		
		when(scheduleRepository.findById(id.id())).thenReturn(Optional.of(buildSchedule(scheduleStartDate)));
		when(generator.generate(new Session(expectedStart), new Session(end))).thenReturn(List.of());
		
		service.findTrainingPrograms(id, start, end);
		
		verify(generator).generate(
				argThat(
						x -> x.getDate().equals(expectedStart) ), argThat(
						y -> true	
								));
		
	}

	@Test
	public void should_start_on_the_start() {
		LocalDate start    			= LocalDate.of(2024, 06, 11);
		LocalDate end 	   			= LocalDate.of(2024, 06, 30);
		ScheduleId id      			= new ScheduleId(1L);
		LocalDate scheduleStartDate = LocalDate.of(2024, 06, 04);
		LocalDate expectedStart 	= LocalDate.of(2024, 06, 11);
		
		when(scheduleRepository.findById(id.id())).thenReturn(Optional.of(buildSchedule(scheduleStartDate)));
		when(generator.generate(new Session(expectedStart), new Session(end))).thenReturn(List.of());
		
		service.findTrainingPrograms(id, start, end);
		
		verify(generator).generate(
				argThat(
						x -> x.getDate().equals(expectedStart) ), argThat(
						y -> true	
								));
		
	}
	

	@ParameterizedTest
	@MethodSource("provideDateData")
	public void testMany(LocalDate start, LocalDate end, LocalDate scheduleStartDate, LocalDate expectedStart) {
		ScheduleId id      			= new ScheduleId(1L);
		
		when(scheduleRepository.findById(id.id())).thenReturn(Optional.of(buildSchedule(scheduleStartDate)));
		when(generator.generate(new Session(expectedStart), new Session(end))).thenReturn(List.of());
		
		service.findTrainingPrograms(id, start, end);
		
		verify(generator).generate(
				argThat(
						x -> x.getDate().equals(expectedStart) ), argThat(
								y -> true	
								));
		Assertions.assertTrue(true);
		
	}
	public  Schedule buildSchedule(LocalDate date) {
		return Schedule.builder()
				.startDate(date)
				.build();
	}

	public static Stream<Arguments> provideDateData() {
		return Stream.of(
				Arguments.of(ld("2024-06-17"), ld("2024-06-30"), ld("2024-06-04"), ld("2024-06-18")),
				Arguments.of(ld("2024-07-03"), ld("2024-07-31"), ld("2024-06-04"), ld("2024-07-09")),
				Arguments.of(ld("2025-01-01"), ld("2025-01-20"), ld("2024-12-15"), ld("2025-01-05"))
				);
	}
	
	private static LocalDate ld(String d) {
		return LocalDate.parse(d);
	}
	
}
