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
	public ResponseEntity<List<Events>> getEventsById(@RequestBody HashMap<String, String> map) throws Exception {
		if (map != null) {
			List<Events> events = null;
			try {
				if (map.containsKey("cultureid")) {
					int eid = Integer.parseInt(map.get("cultureid"));
					if (eid > 0) {
						events = eventRepo.findEventByCultureId(eid);
						if (events.size() > 0) {
							System.out.println("fetched events based on culture from database");
							return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
						} else {
							System.out.println("Query returned null");
							throw new Exception();
						}
					} else {
						System.out.println("invalid culture id");
						throw new Exception();
					}
				} else {
					System.out.println("empty culture id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Events>>(HttpStatus.BAD_REQUEST);
			}
		}

		else {
			System.out.println("Request object is null");
			return new ResponseEntity<List<Events>>(HttpStatus.BAD_REQUEST);
		}
	}
}
