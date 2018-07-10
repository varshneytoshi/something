package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.Items;
@Repository
public interface ItemRepository extends JpaRepository<Items, Integer>{
	@Query("select i from Items i where i.itemType = :itemtype and i.itemPrice = :itemprice and i.delFlag = 0")
	List<Items> findItemsByType(@Param("itemtype") String itype, @Param("itemprice") Double iprice);
	
	@Query("select i.delFlag from Items i where i.itemId = :itemid")
	Integer getDelFlag(@Param("itemid") Integer itid);
}
