package com.hernaval.judomanager.model;

import java.time.LocalDate;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
@AllArgsConstructor
public class Session {
	private final LocalDate date;

	@Override
	public int hashCode() {
		return Objects.hash(date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return date.equals(other.getDate());
	}
	
}
