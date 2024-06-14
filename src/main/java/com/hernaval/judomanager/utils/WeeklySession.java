package com.hernaval.judomanager.utils;

import org.springframework.stereotype.Service;

import com.hernaval.judomanager.model.Session;

@Service
public class WeeklySession implements NextSessionCalculator {
	
	private static final int DAYS_TO_ADD = 7;

	@Override
	public Session getNextSession(Session session) {
		return new Session(session.getDate().plusDays(DAYS_TO_ADD)); 
	}
	
}
