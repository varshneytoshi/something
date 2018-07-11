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

import com.au.entities.Cart;
import com.au.entities.Catering;
import com.au.entities.User;
import com.au.entities.Venue;
import com.au.repositories.CartRepository;
import com.au.repositories.CateringRepository;
import com.au.repositories.UserRepository;

@Controller
public class CateringController {
	@Autowired
	CateringRepository cateringRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartRepo;

	@CrossOrigin
	@GetMapping("/getAllCatering")
	public ResponseEntity<List<Catering>> getCateringList() throws Exception {
		List<Catering> catering = null;
		try {
			catering = cateringRepo.findAll();
			if (catering.size() > 0) {
				System.out.println("Fetched catering items from database");
				return new ResponseEntity<List<Catering>>(catering, HttpStatus.OK);
			} else {
				System.out.println("No items found for catering");
				return new ResponseEntity<List<Catering>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Catering>>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/getCateringItems")
	public ResponseEntity<List<Catering>> getCateringListFiltered(@RequestBody HashMap<String, String> parammap)
			throws Exception {
		if (parammap != null) {
			try {
				if (parammap.containsKey("userId")) {
					User user = userRepo.findById(Integer.parseInt(parammap.get("userId"))).get();
					if (user != null) {
						List<Catering> foodItems = null;
						double priceBound = calculateFoodPriceUpperBound(user.getEstBudget(), user.getNoOfWeddingDays(),
								user.getNoOfGuest());
						foodItems = cateringRepo.findbyPriceAndCulture(priceBound, user.getCulture());
						if (foodItems.size() > 0) {
							System.out.println("Food items fetched from database for this user");
							return new ResponseEntity<List<Catering>>(foodItems, HttpStatus.OK);
						} else {
							System.out.println("Query returned null");
							return new ResponseEntity<List<Catering>>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("Query returned null");
						return new ResponseEntity<List<Catering>>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					System.out.println("empty user id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Catering>>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<List<Catering>>(HttpStatus.BAD_REQUEST);
		}
	}

	private double calculateFoodPriceUpperBound(double estBudget, double noOfDays, int guests) {
		System.out.println("Estimating Budget for catering");
		return estBudget * 0.4 / (noOfDays * guests);
	}

	@CrossOrigin
	@PostMapping("/addCatering")
	public ResponseEntity<Integer> addCatering(@RequestBody HashMap<String, String> credMap) throws Exception {
		if (credMap != null) {
			try {
				if (credMap.containsKey("userId")) {
					int uid = Integer.parseInt(credMap.get("userId"));
					if(uid>0) {
					User user = userRepo.findById(uid).get();
					if (user != null) {
						System.out.println("Fetched user object from database");
						Cart cart = cartRepo.findById(user.getCartId()).get();
						if (cart != null) {
							System.out.println("Fetched cart object from database");
							cart.setMenuId(Integer.parseInt(credMap.get("menuId")));
							cartRepo.save(cart);
							System.out.println("Saved user object to database");
							return new ResponseEntity<Integer>(1, HttpStatus.OK);
						} else {
							System.out.println("Query returned null");
							return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("Query returned null");
						return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
					}}
					else {
						System.out.println("invalid user id");
						throw new Exception();
					}
				} else {
					System.out.println("empty user id");
					throw new Exception();
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
