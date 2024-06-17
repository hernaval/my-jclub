package com.hernaval.judomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hernaval.judomanager.model.Judoka;

@Repository
public interface JudokaRepository extends JpaRepository<Judoka, Long>{
	@Query("select distinct j from Judoka j left join fetch j.trainings")
	List<Judoka> findAsAttenders();
}
