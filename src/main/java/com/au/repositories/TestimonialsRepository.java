package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.au.entities.Testimonials;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer>{
	
}