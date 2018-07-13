
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

import com.au.entities.Catering;
import com.au.entities.Culture;
import com.au.entities.User;
import com.au.repositories.CateringRepository;
import com.au.repositories.CultureRepository;
import com.au.repositories.UserRepository;

@Controller
public class CultureController {
	@Autowired
	CultureRepository culrepo;
	@Autowired
	UserRepository userrepo;

	@CrossOrigin
	@GetMapping("/getAllCulture")
	public ResponseEntity<List<Culture>> getCultureList() throws Exception {
		List<Culture> culture = culrepo.findAll();
		try {
			if (culture.size() > 0) {
				System.out.println("Fetched all cultures from database");
				return new ResponseEntity<List<Culture>>(culture, HttpStatus.OK);
			} else {
				System.out.println("Query returned null");
				return new ResponseEntity<List<Culture>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Culture>>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/setCulture")
	public ResponseEntity<Integer> setCulture(@RequestBody HashMap<String, String> paramMap) throws Exception {
		if (paramMap != null) {
			try {
					
						int userid = Integer.parseInt(paramMap.get("userId"));
						int cultureId = Integer.parseInt(paramMap.get("cultureId"));
						System.out.println("Got user input for userid and cultureid");
						User user = userrepo.findById(userid).get();
						if (user != null) {
							user.setCulture(cultureId);
							userrepo.save(user);
							return new ResponseEntity<Integer>(cultureId, HttpStatus.OK);
						} else {
							System.out.println("Query returned null");
							return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
						}
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
