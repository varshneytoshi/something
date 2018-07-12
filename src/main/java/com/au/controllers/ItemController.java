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

import com.au.entities.Items;
import com.au.entities.Orders;
import com.au.repositories.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository itemRepo;

	// update item type to int and write a function to get the items by cultureid
	@CrossOrigin
	@PostMapping("/getItems")
	public ResponseEntity<List<Items>> getItemsByTypePrice(@RequestBody HashMap<String, String> map) throws Exception {
		if (map != null) {
			List<Items> items = null;
			try {
				if (map.containsKey("itemtype")) {
					items = itemRepo.findItemsByType(map.get("itemtype"));
					if (map.get("itemtype") != null) {
						if (items.size() > 0) {
							System.out.println("Fetched items based on event from database");
							return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<List<Items>>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("invalid item type");
						throw new Exception();
					}
				} else {
					System.out.println("empty item type");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Items>>(HttpStatus.BAD_REQUEST);
			}
		}

		else {
			System.out.println("Request object is null");
			return new ResponseEntity<List<Items>>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@GetMapping("/getAllItems")
	public ResponseEntity<List<Items>> getItems() {
		List<Items> items = null;
		try {
			items = itemRepo.findAll();
			if (items.size() > 0) {
				System.out.println("Fetched items from database");
				return new ResponseEntity<List<Items>>(items, HttpStatus.OK);
			} else {
				System.out.println("Query returned null");
				return new ResponseEntity<List<Items>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Items>>(HttpStatus.BAD_REQUEST);
		}
	}

}
