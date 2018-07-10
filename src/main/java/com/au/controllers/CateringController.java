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

	import com.au.entities.Catering;
import com.au.entities.User;
import com.au.entities.Venue;
import com.au.repositories.CateringRepository;
import com.au.repositories.UserRepository;

	@Controller
	public class CateringController  {
			@Autowired
			CateringRepository cateringRepo;
			@Autowired
			UserRepository userRepo;
			@CrossOrigin
			@GetMapping("/getAllCatering")
			public ResponseEntity<List<Catering>> getCateringList() {
				List<Catering> catering=cateringRepo.findAll();
				return new ResponseEntity<List<Catering>>(catering, HttpStatus.OK);
			}
		
			@CrossOrigin
			@PostMapping("/getCateringItems")
			public ResponseEntity<List<Catering>> getCateringListFiltered(@RequestBody String userId ) {
				User user = userRepo.findById(Integer.parseInt(userId)).get();
				double priceBound=calculateFoodPriceUpperBound(user.getEstBudget(),user.getNoOfWeddingDays(),user.getNoOfGuest());	
				List<Catering> foodItems=cateringRepo.findbyPriceAndCulture(priceBound, user.getCulture());
				return new ResponseEntity<List<Catering>>(foodItems, HttpStatus.OK);
			}
			
			private double calculateFoodPriceUpperBound(double estBudget,double noOfDays,int guests) {
				return estBudget*0.4/(noOfDays*guests);
				
			}
				
			
		
			
			@CrossOrigin
			    @PostMapping("/addCatering")
			    public ResponseEntity<Integer> login(@RequestBody HashMap<String,String> credMap) {
			         return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			    }
		}

	
