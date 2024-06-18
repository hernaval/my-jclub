package com.hernaval.judomanager.utils;

import com.hernaval.judomanager.model.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NextNDaySession implements NextSessionCalculator {

	private int daysToAdd;
	
	@Override
	public Session getNextSession(Session session) {
		return new Session(session.getDate().plusDays(this.daysToAdd));
	}

}
