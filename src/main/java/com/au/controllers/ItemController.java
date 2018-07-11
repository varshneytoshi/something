package com.au.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.Items;
import com.au.entities.Orders;
import com.au.repositories.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository itemRepo;
	//update item type to int and write a function to get the items by cultureid
	@CrossOrigin
	@PostMapping("/getItems")
	public ResponseEntity<List<Items>> getItemsByTypePrice(@RequestBody HashMap<String,String> map) {
			List<Items> items = itemRepo.findItemsByType(map.get("itemtype"));
			return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
	}
}