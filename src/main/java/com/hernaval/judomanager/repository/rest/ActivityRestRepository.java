package com.hernaval.judomanager.repository.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hernaval.judomanager.model.Activity;

@RepositoryRestResource(collectionResourceRel = "activities", path = "activites")
public interface ActivityRestRepository extends JpaRepository<Activity, Long>{

}
