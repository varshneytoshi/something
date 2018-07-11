package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.EventItemMapper;
@Repository
public interface EventItemRepository extends JpaRepository<EventItemMapper, Integer>{


}
