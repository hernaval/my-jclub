package com.hernaval.judomanager.model;

import java.time.LocalDate;

public record AttendanceData(Long trainingSessionId, Long judokaId, Long scheduleId, LocalDate date, boolean present) {

}
