package com.hernaval.judomanager.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hernaval.judomanager.exceptions.SessionPastDuException;
import com.hernaval.judomanager.model.Session;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NextSessionGenerator implements SessionGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(NextSessionCalculator.class);
	
	@Autowired
	private NextSessionCalculator nextSessionCalculator;
	
	@Override
	public List<Session> generate(final Session start, final Session end) {
		if(end.getDate().isBefore(start.getDate())) {
			throw new SessionPastDuException();
		}
		List<Session> nextSessionDates = new ArrayList<>();
		Session       nextSession      = new Session(start.getDate());
		
		while(nextSession.getDate().isBefore(end.getDate())) {
			nextSessionDates.add(nextSession);
		    nextSession = nextSessionCalculator.getNextSession(nextSession);
		    LOGGER.info(nextSession.toString());
		}
		
		return nextSessionDates;
	}
	
	
}
