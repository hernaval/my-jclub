package com.hernaval.judomanager.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(length = 50)
	private String title;
	
	@NotBlank
	@Column
	private String tutor;
	
	@NotBlank
	@Column(length = 100)
	private String location;
	
	@Enumerated(EnumType.STRING)
	private ActivityType type;
	
	@OneToMany(mappedBy = "activity", cascade = CascadeType.PERSIST)
	Set<Schedule> schedules = new HashSet<>();
}
