package com.hernaval.judomanager.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(updatable = false)
	private LocalDate startDate;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIME)
	private LocalTime startTime;
	
	@NotNull
	@Column
	@Temporal(TemporalType.TIME)
	private LocalTime endTime;
	
	private boolean isRecurring;
	
	@Column(nullable = true)
	private ReccurencePattern reccurencePattern;
	
	@Column(nullable = true)
	private int reccurenceInterval;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private DayOfWeek reccurenceDay;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "activity_id")
	// @JsonIgnore
	private Activity activity;
	
	@OneToMany(mappedBy = "schedule")
	Set<TrainingSession> trainings;
	
	public Schedule(Long id) {
		this.id = id;
	}
}
