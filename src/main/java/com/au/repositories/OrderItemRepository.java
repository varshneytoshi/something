package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.entities.OrderItemMapper;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemMapper, Integer>{

}
