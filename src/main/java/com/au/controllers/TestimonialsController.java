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

import com.au.entities.Orders;
import com.au.entities.Testimonials;
import com.au.entities.User;
import com.au.repositories.OrderRepository;
import com.au.repositories.TestimonialsRepository;
import com.au.repositories.UserRepository;

@Controller
public class TestimonialsController {
	@Autowired
	TestimonialsRepository testimonialsRepo;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository ordersRepo;
	
	@CrossOrigin
	@PostMapping("/addNewTestimonial")
	public ResponseEntity<Integer> addTestimonial(@RequestBody HashMap<String, String> paramMap) {
		System.out.println("inside addnewtestimonial");
		Testimonials t = new Testimonials();
		t.setContent(paramMap.get("content"));
		System.out.println(paramMap.get("content"));
		t.setUserId(Integer.parseInt(paramMap.get("userId")));
		System.out.println(paramMap.get("userId"));
		User user =  userRepo.findById(Integer.parseInt(paramMap.get("userId"))).get();
		System.out.println(user.getUserName());
		t.setUserName(user.getUserName());
		testimonialsRepo.save(t);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/checkUserForOrders")
	public ResponseEntity<Integer> checkUser(@RequestBody HashMap<String, String> paramMap) {
		System.out.println("inside checkusers");
		List<Orders> o = ordersRepo.checkUserForOrders(Integer.parseInt(paramMap.get("userId")));
		return new ResponseEntity<Integer>(o.size(),HttpStatus.OK); 
	}
	
	@CrossOrigin
	@GetMapping("/getAllTestimonials")
	public ResponseEntity<List<Testimonials>> getTestimonials(){
		List<Testimonials> list = testimonialsRepo.findAll();
		return new ResponseEntity<List<Testimonials>>(list, HttpStatus.OK);
	}
}
