package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.UsermailId = :mail and u.delFlag = 0")
	User findByMailId(@Param("mail") String foo);
	
	@Query("select u from User u where u.cartId = :cartid and u.delFlag = 0")
	User findUserByCartId(@Param("cartid") Integer foo);
	
	
	@Query("select u.delFlag from User u where u.userId = :userid")
	Integer getDelFlag(@Param("userid") Integer uid);
}
