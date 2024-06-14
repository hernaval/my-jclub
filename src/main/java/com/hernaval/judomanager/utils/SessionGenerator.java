package com.hernaval.judomanager.utils;

import java.util.List;

import com.hernaval.judomanager.model.Session;

public interface SessionGenerator {
	List<Session> generate(Session start, Session to);
}
