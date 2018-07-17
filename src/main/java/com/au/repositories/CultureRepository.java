package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.au.entities.Catering;
import com.au.entities.Culture;
import com.au.entities.Venue;

public interface CultureRepository extends JpaRepository<Culture, Integer> {
	@Query("select c.cultureId from Culture c where c.cultureName = :culturename")
	Integer findCultureByName(@Param("culturename") String cname);
}
