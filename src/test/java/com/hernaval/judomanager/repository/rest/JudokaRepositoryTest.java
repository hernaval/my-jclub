package com.hernaval.judomanager.repository.rest;

import static com.hernaval.judomanager.model.Belt.WHITE;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hernaval.judomanager.model.Judoka;
import com.hernaval.judomanager.model.TrainingSession;
import com.hernaval.judomanager.repository.JudokaRepository;

@DataJpaTest
public class JudokaRepositoryTest {

	@Autowired
	private JudokaRepository repository;
	
	@Test
	public void testFinAttenders() {
		List<Judoka> judokaList = IntStream.range(0, 8).mapToObj(i -> buildJudoka(i, i % 2 == 0)).toList();
		repository.saveAll(judokaList);
	
		List<Judoka> attenders  = repository.findAsAttenders();
		List<Judoka> attendersWithoutTraining = attenders.stream().filter(a -> a.getTrainings().size() == 0).toList();
		
		Assertions.assertEquals(8, attenders.size());
		Assertions.assertEquals(4, attendersWithoutTraining.size());
		
	}
	
	
	private Judoka buildJudoka(int i, boolean withTraining) {
		return Judoka.builder()
				.firstname("Jdk-"+i)
				.lastname("lst"+i)
				.address("Address")
				.belt(WHITE)
				.registrationDate(LocalDate.of(2024, 06, 06))
				.birthDate(LocalDate.of(2024, 06, 06))
				.trainings(withTraining ? Set.of(new TrainingSession()) : Set.of())
				.build();
	}

}
