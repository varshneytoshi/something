package com.au.controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.entities.User;
import com.au.repositories.UserRepository;


@Controller
public class ApplicationController {
//	@RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }

	@Autowired
	UserRepository userRepo;
	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	
	 @GetMapping("/addNewUser")
	    public String userForm(Model model) {
	        model.addAttribute("user", new User());
	        return "addNewUser";
	    }

	    @PostMapping("/addNewUser")
	    public String greetingSubmit(@ModelAttribute User user) {
	    	userRepo.save(user);
	    	
	        return "result";
	    }
	    @CrossOrigin
	    @PostMapping("/login")
	    public ResponseEntity<Integer> login(@RequestBody HashMap<String,String> credMap) {
	    	List<User> users=userRepo.findAll();
    		for(User u : users)
	    	{
	    		if(u.getUsermailId().equals(credMap.get("email")))
	    				{
	    			if(u.getUserPass().equals(credMap.get("password")))
	    			{
	    				return new ResponseEntity<Integer>(u.getUserId(),HttpStatus.OK);
	    			}else
	    				return new ResponseEntity<Integer>(0,HttpStatus.NOT_ACCEPTABLE);
	    				}
	    	}
	        return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
	    }
	    
	    @CrossOrigin
	    @PostMapping("/anotherlogin")
	    public ResponseEntity<Integer> anotherlogin(@RequestBody HashMap<String,String> credMap) {
	    	User user=userRepo.findByMailId(credMap.get("email"));
    		if(user!=null){
    			if(user.getUserPass().equals(credMap.get("password")))
	    		{
	    				return new ResponseEntity<Integer>(user.getUserId(),HttpStatus.OK);
	    		}
    			else
	    			return new ResponseEntity<Integer>(0,HttpStatus.NOT_ACCEPTABLE);
	    	}
	        return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
	    }

		@GetMapping("/getUsers")
		public ResponseEntity<List<User>> getArticleById() {
			List<User> users=userRepo.findAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	
		@CrossOrigin
	    @PostMapping("/cart")
	    public ResponseEntity<User> getUserByCart(@RequestBody HashMap<String,String> map) {
	    	User user = userRepo.findUserByCartId(Integer.parseInt(map.get("cartid")));
	    	return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	    
	    @CrossOrigin
	    @PostMapping("/getById")
	    public ResponseEntity<User> getUserById(@RequestBody HashMap<String,String> map) {
	    	User user = userRepo.findById(Integer.parseInt(map.get("userid"))).get();
	    	return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	    
//	    @CrossOrigin
//	    @PostMapping("/updateuser")
//	    public void updateUser(@RequestBody HashMap<String,String> map) throws Exception {
//	    	if(!userRepo.existsById(Integer.parseInt(map.get("userid"))))
//	    	{
//	    		throw new Exception("user doesn't exist");
//	    	}
//	    	User user = userRepo.findById(Integer.parseInt(map.get("userid"))).get();
//	    	
//	    	userRepo.save(user);
//	    }
	    
	    @CrossOrigin
	    @PostMapping("/deleteuser")
	    public ResponseEntity<Integer> deleteUser(@RequestBody HashMap<String,String> map) throws Exception {
//	        if(!userRepo.existsById(Integer.parseInt(map.get("userid")))) {
	    	if(userRepo.getDelFlag(Integer.parseInt(map.get("userid")))==1){
	            throw new Exception("user doesn't exist");
	        }
//	        userRepo.updateDelFlag(Integer.parseInt(map.get("userid")));
	    	User user = userRepo.findById(Integer.parseInt(map.get("userid"))).get();
	    	user.setDelFlag(1);
	    	userRepo.save(user);
//	        return userRepo.findById(Integer.parseInt(map.get("userid")))
//	                .map(user -> {
//	                    userRepo.delete(user);
//	                    return ResponseEntity.ok().build();
//	                }).orElseThrow(() -> new Exception("User not found with id " + Integer.parseInt(map.get("userid"))));
	    	return new ResponseEntity<Integer>(1, HttpStatus.OK);
	    }
}
