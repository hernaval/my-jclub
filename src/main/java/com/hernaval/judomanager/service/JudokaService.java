package com.hernaval.judomanager.service;

import java.util.List;
import java.util.Optional;

import com.hernaval.judomanager.model.Judoka;

public interface JudokaService {
	Judoka register(Judoka judoka);
	List<Judoka> findAll();
	Optional<Judoka> findById(long id);
}
