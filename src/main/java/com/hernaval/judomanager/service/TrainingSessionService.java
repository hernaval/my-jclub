package com.hernaval.judomanager.service;

import java.time.LocalDate;
import java.util.List;

import com.hernaval.judomanager.model.AttendanceData;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;

public interface TrainingSessionService {
	List<Session> findTrainingPrograms(ScheduleId schedule, LocalDate startDate, LocalDate endDate);
	void          updateAttendance(List<AttendanceData> data);
}
