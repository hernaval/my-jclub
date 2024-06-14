package com.hernaval.judomanager.repository.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hernaval.judomanager.model.Judoka;

@RepositoryRestResource(collectionResourceRel = "judokas", path="judokas")
public interface JudokaRestResourceRepository extends JpaRepository<Judoka, Long> {
	@Query("select j from Judoka j where j.firstname like :name or j.lastname like :name ")
	List<Judoka> findByName(@Param("name") String name);
}
