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

import com.au.entities.Catering;
import com.au.entities.Culture;
import com.au.entities.Events;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CateringControllerTest {
	@Autowired
	CultureController cultureController;
	@Autowired
	private TestRestTemplate restTemplate;

	@org.junit.Before
	public void start() {
		System.out.println("Before test cases");
	}

	@Test
	public void checkGetAllCatering() {
		ResponseEntity<Catering[]> responseEntity = restTemplate.getForEntity("/getAllCatering", Catering[].class);
		Catering[] catering = responseEntity.getBody();
		System.out.println(catering);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void checkSetCulture() {
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", 1);
		map.put("cultureId", 2);
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("/setCulture", map, Integer.class);
		int result = responseEntity.getBody();
		System.out.println(result);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
