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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EventControllerTest {
	@Autowired
	EventController eventController;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void checkGetEvents() {
		Map<String,Integer> map = new HashMap<>();
		map.put("cultureid", 1);
		ResponseEntity<Events[]> responseEntity = restTemplate.postForEntity("/getevents", map, Events[].class);
		Events[] events = responseEntity.getBody();
		System.out.println(events);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
