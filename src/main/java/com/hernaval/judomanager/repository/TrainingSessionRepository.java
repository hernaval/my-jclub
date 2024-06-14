package com.hernaval.judomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hernaval.judomanager.model.TrainingSession;

@Repository
public interface TrainingSessionRepository extends  JpaRepository<TrainingSession, Long>{

}
