package com.au.controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.au.entities.Events;
import com.au.entities.Items;
import com.au.entities.Orders;
import com.au.entities.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ApplicationControllerTest {
	@Autowired
	OrderController orderController;
	@Autowired
	private TestRestTemplate restTemplate;

	@org.junit.Before
	public void start() {
		System.out.println("Before test cases");
	}

	@Test
	public void checkGetOrders() {
		Map<String,String> map = new HashMap<>();
		map.put("userName", "gayu");
		map.put("userPass", "123123");
		map.put("usermailId", "gayu@gmail");
		map.put("userContact", "12134");
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("/addNewUser", map, Integer.class);
		int user = responseEntity.getBody();
		System.out.println(user);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkLogin() {
		Map<String,String> map = new HashMap<>();
		map.put("email", "gayu@gmail");
		map.put("password", "123123");
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("/login", map, Integer.class);
		int user = responseEntity.getBody();
		System.out.println(user);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkGetAllUsers() {
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("/getUsers", User[].class);
		User[] user = responseEntity.getBody();
		System.out.println(user);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
