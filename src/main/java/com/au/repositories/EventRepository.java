package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.Events;
import com.au.entities.Orders;

@Repository
public interface EventRepository extends JpaRepository<Events, Integer>{
	@Query("select e from Events e where e.cultureId = :cultureid and e.delFlag = 0")
	List<Events> findEventByCultureId(@Param("cultureid") Integer culid);
}