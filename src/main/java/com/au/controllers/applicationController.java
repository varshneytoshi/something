package com.au.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.au.entities.User;


@Controller
public class applicationController {
//	@RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
	
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
	        return "result";
	    }
	
	
	
}
