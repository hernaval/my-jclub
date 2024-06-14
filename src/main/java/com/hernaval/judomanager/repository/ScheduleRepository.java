package com.hernaval.judomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.hernaval.judomanager.model.Schedule;

@Repository
@RestResource(exported = false)
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
