
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
	public ResponseEntity<List<Culture>> getCateringList() {
		List<Culture> culture=culrepo.findAll();
		return new ResponseEntity<List<Culture>>(culture, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/setCulture")
	public ResponseEntity<Integer> setCulture(@RequestBody HashMap<String,String> paramMap) {
		int userid=Integer.parseInt(paramMap.get("userId"));
		int cultureId=Integer.parseInt(paramMap.get("cultureId"));
		User user=userrepo.findById(userid).get();
		user.setCulture(cultureId);
		userrepo.save(user);
		return new ResponseEntity<Integer>(cultureId, HttpStatus.OK);
	}



}
