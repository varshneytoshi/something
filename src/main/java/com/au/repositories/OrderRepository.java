package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>{
	@Query("select o from Orders o where o.userId = :userid and o.delFlag = 0")
	List<Orders> findOrderByUserId(@Param("userid") Integer uid);
	
	@Query("select o.delFlag from Orders o where o.orderId = :orderid")
	Integer getDelFlag(@Param("orderid") String oid);
	
//	@Query("insert into Orders o (values) :orderid, :itemid")
//	void insertOrderItem(@Param("orderid") Integer oid, @Param("itemid") Integer itid);
}
