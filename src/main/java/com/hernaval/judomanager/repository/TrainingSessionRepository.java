package com.hernaval.judomanager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hernaval.judomanager.model.TrainingSession;


// @Repository
public interface TrainingSessionRepository extends  JpaRepository<TrainingSession, Long>{
	boolean existsByDateAndSchedule_Id(LocalDate date, Long scheduleId);
	List<TrainingSession> findByDateAndSchedule_Id(LocalDate date, Long schedule_Id);
}
