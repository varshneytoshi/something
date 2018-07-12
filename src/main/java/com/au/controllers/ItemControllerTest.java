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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ItemControllerTest {
	@Autowired
	EventController eventController;
	@Autowired
	private TestRestTemplate restTemplate;

	@org.junit.Before
	public void start() {
		System.out.println("Before test cases");
	}

	@Test
	public void checkGetItemsByType() {
		Map<String,String> map = new HashMap<>();
		map.put("itemtype", "decor");
		ResponseEntity<Items[]> responseEntity = restTemplate.postForEntity("/getItems", map, Items[].class);
		String items = responseEntity.getBody().toString();
		System.out.println(items);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkGetAllItems() {
		ResponseEntity<Items[]> responseEntity = restTemplate.getForEntity("/getAllItems", Items[].class);
		Items[] items = responseEntity.getBody();
		System.out.println(items);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
