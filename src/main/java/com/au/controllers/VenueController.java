package com.au.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.User;
import com.au.entities.Venue;
import com.au.repositories.UserRepository;
import com.au.repositories.VenueRepository;

@Controller
public class VenueController {

	@Autowired
	VenueRepository venueRepo;
	@Autowired
	UserRepository userRepo;

	@CrossOrigin
	@PostMapping("/getVenues")
	public ResponseEntity<List<Venue>> getVenueList(@RequestBody HashMap<String, String> filters) {
		if (filters != null) {
			List<Venue> venues = null;
			try {
				if (filters.containsKey("userId")) {
					int uid = Integer.parseInt(filters.get("userId"));
					if (uid > 0) {
						User user = userRepo.findById(uid).get();
						if (user != null) {
							System.out.println("Fetched user object from database");
							double priceBound = calculateVenuePriceUpperBound(user.getEstBudget(),
									user.getNoOfWeddingDays());
							String location = filters.get("venueLocation");
							int noOfGuests = user.getNoOfGuest();
							if (location != null && noOfGuests > 0) {
								System.out.println("Got user input for location and no of guests");
								venues = venueRepo.findbyPriceAndLocation(priceBound, location, noOfGuests);
								if (venues.size() > 0) {
									System.out.println("Returning list of venues");
									return new ResponseEntity<List<Venue>>(venues, HttpStatus.OK);
								} else {
									System.out.println("No venues have been added by this user");
									throw new Exception();
								}

							} else {
								System.out.println("no input fetched for location and no of guests");
								throw new Exception();
							}
						} else {
							System.out.println("Query returned null");
							return new ResponseEntity<List<Venue>>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("invalid user id");
						throw new Exception();
					}
				} else {
					System.out.println("empty user id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Venue>>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<List<Venue>>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/getVenueDetails")
	public ResponseEntity<Venue> getVenueById(@RequestBody HashMap<String, String> venueMap) {
		if (venueMap != null) {
			try {
				if (venueMap.containsKey("venueId")) {
					int vid = Integer.parseInt(venueMap.get("venueId"));
					if (vid > 0) {
						Venue venue = venueRepo.findById(vid).get();
						if (venue != null) {
							System.out.println("Fetched venue object from database");
							return new ResponseEntity<Venue>(venue, HttpStatus.OK);
						} else {
							System.out.println("query returned null");
							return new ResponseEntity<Venue>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("invalid venue id");
						throw new Exception();
					}
				} else {
					System.out.println("empty venue id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Venue>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<Venue>(HttpStatus.BAD_REQUEST);
		}
	}

	private double calculateVenuePriceUpperBound(double estBudget, double noOfDays) {
		System.out.println("Estimating budget for venue");
		return estBudget * 0.4 / noOfDays;
	}

}
