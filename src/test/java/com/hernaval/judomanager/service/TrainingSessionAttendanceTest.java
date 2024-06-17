package com.hernaval.judomanager.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hernaval.judomanager.model.Judoka;
import com.hernaval.judomanager.model.Schedule;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.TrainingSession;
import com.hernaval.judomanager.model.dto.Attender;
import com.hernaval.judomanager.repository.JudokaRepository;
import com.hernaval.judomanager.repository.TrainingSessionRepository;
import com.hernaval.judomanager.service.impl.TrainingSessionServiceImpl;

@Testable
@ExtendWith(MockitoExtension.class)
public class TrainingSessionAttendanceTest {
	
	@InjectMocks
	TrainingSessionServiceImpl service;
	
	@Mock 
	TrainingSessionRepository trainingRepository;
	
	@Mock
	JudokaRepository judokaRepository;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void should_get_attenders_with_empty_attendance() {
		ScheduleId scheduleId         = new ScheduleId(null);
		final LocalDate date          = LocalDate.now();
		final List<Judoka> judokaList = IntStream.range(0, 8).mapToObj(i -> buildJudoka()).toList();
		
		when(trainingRepository.existsByDateAndSchedule_Id(date, scheduleId.id())).thenReturn(false);
		when(judokaRepository.findAll()).thenReturn(judokaList);
		final List<Attender> attenders = service.findAttenders(scheduleId, date);
		
		Assertions.assertTrue(attenders.stream().allMatch(a -> a.present() == null && a.date() == null ));
		
		verify(judokaRepository, times(1)).findAll();
		verify(trainingRepository, times(0)).findByDateAndSchedule_Id(date, scheduleId.id());
	}
	
	@Test
	public void should_get_attenders_with_attendance() {
		ScheduleId scheduleId                    = new ScheduleId(null);
		final LocalDate date                     = LocalDate.now();
		final List<TrainingSession> tSessionList = LongStream.range(0, 8).mapToObj(i -> buildTS(i)).toList();
		
		when(trainingRepository.existsByDateAndSchedule_Id(date, scheduleId.id())).thenReturn(true);
		when(trainingRepository.findByDateAndSchedule_Id(date, scheduleId.id())).thenReturn(tSessionList);
		final List<Attender> attenders = service.findAttenders(scheduleId, date);
		
		Assertions.assertTrue(attenders.stream().allMatch(a -> a.present() != null && a.date() != null && a.trainingSessionId() != null ));
		
		verify(trainingRepository, times(1)).findByDateAndSchedule_Id(date, scheduleId.id());
		verify(judokaRepository, times(0)).findAll();
	}
	
	private Judoka buildJudoka() {
		return Judoka.builder()
				.firstname("Jdk")
				.lastname("Ippon")
				.build();
	}

	public  Schedule buildSchedule(Long id) {
		return Schedule.builder()
				.id(id)
				.build();
	}
	
	public TrainingSession buildTS(Long id) {
		return TrainingSession.builder()
				.id(id)
				.date(LocalDate.now())
				.isPresent(true)
				.judoka(buildJudoka())
				.build();
	}
}
