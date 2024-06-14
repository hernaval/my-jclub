package com.hernaval.judomanager.utils;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.hernaval.judomanager.exceptions.SessionPastDuException;
import com.hernaval.judomanager.model.Session;

@Testable
public class NextSessionGeneratorTest {
	SessionGenerator generator;
	NextSessionCalculator calculator;
	Session session1;
	Session session2;
	
	@BeforeEach
	public void init() {
		session1   = new Session(LocalDate.of(2024, 06, 04));
		session2   = new Session(LocalDate.of(2024, 06, 30));
		calculator = new WeeklySession(); 
		generator  = new NextSessionGenerator(calculator);
	}
	
	@Test
	public void should_have_correct_session_size() {
		List<Session> results = generator.generate(session1, session2);
		
		Assertions.assertEquals(4, results.size());
	}
	
	
	@Test
	public void should_have_correct_last_session() {
		List<Session> results = generator.generate(session1, session2);
		Session expected      = new Session(LocalDate.of(2024, 06, 25));
		
		Assertions.assertEquals(4, results.size());
		Assertions.assertTrue(expected.equals(results.get(results.size() -1)));
	}
	
	@Test
	public void should_throw_exception_start_is_before_end() {
		Session start = new Session(LocalDate.of(2024, 06, 04));
		Session end   = new Session(LocalDate.of(2024, 06, 01));
		
		Assertions.assertThrows(SessionPastDuException.class, ()->  generator.generate(start, end));
		
	}
}
