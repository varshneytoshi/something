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
import com.au.entities.Venue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class VenueControllerTest {
	@Autowired
	VenueController venueController;
	@Autowired
	private TestRestTemplate restTemplate;

	@org.junit.Before
	public void start() {
		System.out.println("Before test cases");
	}

	@Test
	public void checkGetVenues() {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", "1");
		map.put("price", "1200000");
		map.put("location", "goa");

		ResponseEntity<Venue[]> responseEntity = restTemplate.postForEntity("/getVenues", map, Venue[].class);
		Venue[] venues = responseEntity.getBody();
		System.out.println(venues);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkGetVenueDetails() {
		Map<String, Integer> map = new HashMap<>();
		map.put("venueId", 1);
		ResponseEntity<Venue> responseEntity = restTemplate.postForEntity("/getVenueDetails", map, Venue.class);
		Venue venue = responseEntity.getBody();
		System.out.println(venue);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
