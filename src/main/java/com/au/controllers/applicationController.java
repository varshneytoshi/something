package com.au.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.entities.User;
import com.au.repositories.Userrepository;;


@Controller
public class applicationController {
//	@RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
	
	@Autowired
	Userrepository userrepo;
	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        System.out.println(userrepo.findById(2).get().getUserName());
        return "greeting";
    }
	
	 @GetMapping("/addNewUser")
	    public String userForm(Model model) {
	        model.addAttribute("user", new User());
	        System.out.println(userrepo.findById(1).get().getUserName());
	        System.out.println("232323");
	        return "addNewUser";
	    }

	    @PostMapping("/addNewUser")
	    public String greetingSubmit(@ModelAttribute User user) {
	    	userrepo.save(user);
	        return "result";
	    }
	
	    @CrossOrigin
	    @PostMapping("/login")
	    public ResponseEntity<Integer> login(@RequestBody HashMap<String,String> credMap) {
	    	List<User> users=userrepo.findAll();
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
	    	User user=userrepo.findByMailId(credMap.get("email"));
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
			List<User> users=userrepo.findAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	
}
