package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.au.entities.Catering;
import com.au.entities.Culture;

public interface CultureRepository extends JpaRepository<Culture, Integer> {

}
