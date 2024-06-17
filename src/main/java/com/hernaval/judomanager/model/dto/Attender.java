package com.hernaval.judomanager.model.dto;

import java.time.LocalDate;

public record Attender(Long trainingSessionId, String firstname, String lastname, LocalDate date, Boolean present) {

}
