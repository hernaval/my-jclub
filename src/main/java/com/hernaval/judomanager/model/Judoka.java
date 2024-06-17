package com.hernaval.judomanager.model;


import static jakarta.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Judoka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	private String firstname;
	
	@NotBlank
	@Column(length = 100, nullable = false)
	private String lastname;
	
	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate birthDate;
	
	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate registrationDate;
	
	@NotBlank
	@Column(length = 150, nullable = false)
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Belt belt;
	
	@OneToMany(mappedBy = "judoka", fetch = LAZY)
	Set<TrainingSession> trainings;
	
	public Judoka(Long id) {
		this.id = id;
	}
}
