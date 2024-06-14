package com.hernaval.judomanager.model;

import java.time.LocalDate;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSession {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne 
	@JoinColumn(name= "judoka_id")
	@RestResource(exported = false)
	private Judoka judoka;
	
	@ManyToOne
	@JoinColumn(name="schedule_id")
	private Schedule schedule;
	
	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	@Column(nullable = true)
	private boolean isPresent;
}


