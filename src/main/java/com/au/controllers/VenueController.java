package com.au.controllers;

import java.util.HashMap;
import java.util.List;

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
		User user = userRepo.findById(Integer.parseInt(filters.get("userId"))).get();
		double priceBound=calculateVenuePriceUpperBound(user.getEstBudget(),user.getNoOfWeddingDays());	
		String location=filters.get("Location");
		int noOfGuests=user.getNoOfGuest();
		List<Venue> venues=venueRepo.findbyPriceAndLocation(priceBound, location, noOfGuests);
		return new ResponseEntity<List<Venue>>(venues, HttpStatus.OK);
	}
	
	private double calculateVenuePriceUpperBound(double estBudget,double noOfDays) {
		return estBudget*0.4/noOfDays;
		
	}

}
