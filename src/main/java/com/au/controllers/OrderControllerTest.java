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
import com.au.entities.Orders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OrderControllerTest {
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
		Map<String,Integer> map = new HashMap<>();
		map.put("userid", 1);
		ResponseEntity<Orders[]> responseEntity = restTemplate.postForEntity("/getOrders", map, Orders[].class);
		Orders[] orders = responseEntity.getBody();
		System.out.println(orders);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkDeleteOrder() {
		Map<String,Integer> map = new HashMap<>();
		map.put("orderid", 1);
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("/deleteorder", map, Integer.class);
		int result = responseEntity.getBody();
		System.out.println(result);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
