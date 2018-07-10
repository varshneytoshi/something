package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.Cart;
import com.au.entities.Items;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	@Query("select c.delFlag from Cart c where c.cartId = :cartid")
	Integer getDelFlag(@Param("cartid") Integer cid);
	
	@Query("select ei.itemId from EventItemMapper ei where ei.cartId = :cartid and ei.delFlag = 0")
	List<Integer> getItems(@Param("cartid") Integer cid);
}
