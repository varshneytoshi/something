package com.au.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.EventItemMapper;
import com.au.entities.OrderItemMapper;
import com.au.entities.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders, String>{
	@Query("select o from Orders o where o.userId = :userid and o.delFlag = 0")
	List<Orders> findOrderByUserId(@Param("userid") Integer uid);
	
	@Query("select o from Orders o where o.orderId = :orderid")
	Orders getOrders(@Param("orderid") String oid);
	
	@Query("select oi from OrderItemMapper oi where oi.orderId = :orderid and oi.delFlag = 0")
	List<OrderItemMapper> getItems(@Param("orderid") String orderId);

//	@Query("select o from Orders o where o.userId = :userid order by orderId desc limit 1")
//	Optional<Orders> getOrderByUserId(@Param("orderid") Integer uid);

	@Query("select o from Orders o where o.userId = :userid")
	List<Orders> checkUserForOrders(@Param("userid") Integer uid);
}
