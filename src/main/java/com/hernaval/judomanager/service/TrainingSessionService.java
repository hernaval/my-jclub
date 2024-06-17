package com.hernaval.judomanager.service;

import java.time.LocalDate;
import java.util.List;

import com.hernaval.judomanager.model.AttendanceData;
import com.hernaval.judomanager.model.ScheduleId;
import com.hernaval.judomanager.model.Session;
import com.hernaval.judomanager.model.dto.Attender;

public interface TrainingSessionService {
	List<Session> findTrainingPrograms(ScheduleId schedule, LocalDate startDate, LocalDate endDate);
	void          updateAttendance(List<AttendanceData> data);
	List<Attender> findAttenders(ScheduleId id, LocalDate date);
}
