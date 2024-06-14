package com.hernaval.judomanager.utils;

import com.hernaval.judomanager.model.Session;

public interface NextSessionCalculator {
	Session getNextSession(Session session);
}
