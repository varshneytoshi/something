package com.au.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.Culture;
import com.au.entities.Events;
import com.au.entities.Orders;
import com.au.repositories.CultureRepository;
import com.au.repositories.EventRepository;

@Controller
public class EventController {
	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	CultureRepository culRepo;

	@CrossOrigin
	@PostMapping("/getevents")
	public ResponseEntity<List<Events>> getEventsById(@RequestBody HashMap<String, String> map) throws Exception {
		if (map != null) {
			List<Events> events = null;
			try {
				if (map.containsKey("cultureId")) {
					int eid = Integer.parseInt(map.get("cultureId"));
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
	
	@CrossOrigin
	@GetMapping("/addNewEvent")
	public String userForm(Model model) {
		model.addAttribute("events", new Events());
		return "addNewEvent";
	}
	
	@CrossOrigin
	@PostMapping("/addNewEvent")
	public ResponseEntity<Integer> saveCulture(@RequestBody HashMap<String, String> eventDetails) {
		System.out.println("Inside Post method");
		if (null != eventDetails) {
			try {
				Events events = new Events();
				if (eventDetails.containsKey("eventName")) {
					String eventName = eventDetails.get("eventName");
					if (!eventName.isEmpty()) {
						events.setEventName(eventName);
					} else {
						System.out.println("Request Event name parameter empty");
						throw new Exception();
					}
					
				} else {
					System.out.println("Request object does not contain eventName key");
					throw new Exception();
				}
				
				if (eventDetails.containsKey("cultureName")) {
					String cultureName = eventDetails.get("cultureName");
					if (!cultureName.isEmpty()) {
						int cultureid = culRepo.findCultureByName(cultureName);
						events.setCultureId(cultureid);
					} else {
						System.out.println("Request Culture name parameter empty");
						throw new Exception();
					}
					
				} else {
					System.out.println("Request object does not contain cultureName key");
					throw new Exception();
				}
				events.setDelFlag(0);
				eventRepo.save(events);
				return new ResponseEntity<Integer>(1, HttpStatus.OK);				
			}catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		}else {
			System.out.println("The request object is null");
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
