package com.hernaval.judomanager.repository.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hernaval.judomanager.model.TrainingSession;

@RepositoryRestResource(collectionResourceRel = "trainings", path = "trainings")
public interface TrainingSessionRestResourceRepository extends JpaRepository<TrainingSession, Long>{
}
