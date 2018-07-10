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
	import com.au.repositories.CateringRepository;

	@Controller
	public class CateringController  {
			@Autowired
			CateringRepository catrepo;
			@CrossOrigin
			@GetMapping("/getAllCatering")
			public ResponseEntity<List<Catering>> getCateringList() {
				List<Catering> catering=catrepo.findAll();
				return new ResponseEntity<List<Catering>>(catering, HttpStatus.OK);
			}
		
			@CrossOrigin
			@PostMapping("/getCateringFiltered")
			public ResponseEntity<List<Catering>> getCateringListFiltered(@RequestBody HashMap<String,String> filterMap) {
				float limit=Float.parseFloat(filterMap.get("budget"));
				float numberOfPersons=Float.parseFloat(filterMap.get("noOfPerson"));
				if(limit<=0||numberOfPersons<=0)
				return getCateringList();
				System.out.println(numberOfPersons+" "+numberOfPersons);
				List<Catering> catering=catrepo.findbyfilter(new Float(limit/numberOfPersons));
				return new ResponseEntity<List<Catering>>(catering, HttpStatus.OK);
			}
		
			
			@CrossOrigin
			    @PostMapping("/addCatering")
			    public ResponseEntity<Integer> login(@RequestBody HashMap<String,String> credMap) {
			         return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
			    }
		}

	
