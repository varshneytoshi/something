package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.au.entities.Catering;
import com.au.entities.User;

public interface CateringRepository extends JpaRepository<Catering, Integer>{

	@Query("select c from Catering c where c.pricePerPlate< :limit") 
	List<Catering> findbyfilter(@Param("limit") double lim);

}