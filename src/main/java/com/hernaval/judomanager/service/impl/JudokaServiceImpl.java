package com.hernaval.judomanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hernaval.judomanager.model.Judoka;
import com.hernaval.judomanager.repository.JudokaRepository;
import com.hernaval.judomanager.service.JudokaService;

@Service
public class JudokaServiceImpl implements JudokaService {

	@Autowired
	private JudokaRepository repository;
	
	@Override
	public Judoka register(Judoka judoka) {
		return repository.save(judoka);
	}

	@Override
	public List<Judoka> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Judoka> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
