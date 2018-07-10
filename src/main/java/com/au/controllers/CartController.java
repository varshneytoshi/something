package com.au.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.au.entities.Cart;
import com.au.entities.Items;
import com.au.entities.Orders;
import com.au.entities.User;
import com.au.repositories.CartRepository;
import com.au.repositories.OrderRepository;
import com.au.repositories.EventItemRepository;
import com.au.repositories.UserRepository;

@Controller
public class CartController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	EventItemRepository eiRepo;
	@Autowired
	OrderRepository orderRepo;
	
	private User getUser(int userID) {
		return userRepo.findById(userID).get();
	}
	
	
	@CrossOrigin
	@PostMapping("/setVenue")
	public ResponseEntity<Integer> setVenue(@RequestBody HashMap<String, String> venueObject) {
		User user = getUser(Integer.parseInt(venueObject.get("userId")));
		int cartid=user.getCartId();
		Cart cart=cartRepo.findById(cartid).get();
		cart.setVenueId(Integer.parseInt(venueObject.get("venueId")));
		cartRepo.save(cart);			
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/setfoodPackage")
	public ResponseEntity<Integer> setMenu(@RequestBody HashMap<String, String> foodObject) {
		User user = getUser(Integer.parseInt(foodObject.get("userId")));
		int cartid=user.getCartId();
		Cart cart=cartRepo.findById(cartid).get();
		cart.setVenueId(Integer.parseInt(foodObject.get("packageId")));
		cartRepo.save(cart);			
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/setItem")
	public ResponseEntity<Integer> setitem(@RequestBody HashMap<String, String> itemsObject) {
		int userId=Integer.parseInt(itemsObject.get("userId"));
		User user=getUser(userId);
		int cartId=user.getCartId();
		int itemId=Integer.parseInt(itemsObject.get("itemId"));
		int eventId=Integer.parseInt(itemsObject.get("eventId"));
		EventItemMapper eiMapper=new EventItemMapper();
		eiMapper.setCartId(cartId);
		eiMapper.setEventId(eventId);
		eiMapper.setItemId(itemId);
		eiRepo.save(eiMapper);	
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	@CrossOrigin
    @PostMapping("/deletecart")
    public ResponseEntity<Integer> deleteCart(@RequestBody HashMap<String,String> map, Model model) throws Exception{
    	if(cartRepo.getDelFlag(Integer.parseInt(map.get("cartid")))==1){
            throw new Exception("cart doesn't exist");
        }
    	Cart cart = cartRepo.findById(Integer.parseInt(map.get("cartid"))).get();
    	cart.setDelFlag(1);
    	cart.setVenueId(0);
    	cart.setMenuId(0);
    	cartRepo.save(cart);
    	
    	Orders order = new Orders();
    	order.setMenuId(cart.getMenuId());
    	order.setVenueId(cart.getVenueId());
    	order.setTotalPrice(User.totalPrice);
    	User user = userRepo.findUserByCartId(Integer.parseInt(map.get("cartid")));
    	order.setUserId(user.getUserId());
    	order.setDelFlag(0);
    	List<Items> items = cartRepo.getItems(Integer.parseInt(map.get("cartid")));
    	order.setItemsPurchased(items);
    	orderRepo.save(order);
    	return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
	
}
