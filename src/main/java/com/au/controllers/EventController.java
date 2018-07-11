package com.au.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.Events;
import com.au.entities.Orders;
import com.au.repositories.EventRepository;

@Controller
public class EventController {
	@Autowired
	EventRepository eventRepo;
	
	@CrossOrigin
	@PostMapping("/getevents")
	public ResponseEntity<List<Events>> getEventsById(@RequestBody HashMap<String,String> map) {
			List<Events> events = eventRepo.findOrderByCultureId(Integer.parseInt(map.get("cultureId")));
			return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
	}
}
