package com.au.controllers;

import java.util.ArrayList;
import java.util.Date;
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


import com.au.entities.Catering;
import com.au.entities.EventItemDetails;
import com.au.entities.EventItemMapper;
import com.au.entities.Events;
import com.au.entities.Items;
import com.au.entities.OrderItemMapper;
import com.au.entities.Orders;
import com.au.entities.User;
import com.au.entities.Venue;
import com.au.repositories.CartRepository;
import com.au.repositories.CateringRepository;
import com.au.repositories.OrderRepository;
import com.au.repositories.EventItemRepository;
import com.au.repositories.EventRepository;
import com.au.repositories.ItemRepository;
import com.au.repositories.OrderItemRepository;
import com.au.repositories.UserRepository;
import com.au.repositories.VenueRepository;

@Controller
public class CartController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	EventItemRepository eiRepo;
	@Autowired
	OrderItemRepository oiRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	VenueRepository venueRepo;
	@Autowired
	CateringRepository catRepo;
	@Autowired
	EventRepository eventRepo;
	private User getUser(int userID) {
		return userRepo.findById(userID).get();
	}
	
	@CrossOrigin
	@PostMapping("/setVenue")
	public ResponseEntity<Integer> setVenue(@RequestBody HashMap<String, String> venueObject) {
		User user = getUser(Integer.parseInt(venueObject.get("userId")));
		String cartid=user.getCartId();
		Cart cart=cartRepo.findById(cartid).get();
		cart.setVenueId(Integer.parseInt(venueObject.get("venueId")));
		cartRepo.save(cart);			
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/setFoodPackage")
	public ResponseEntity<Integer> setMenu(@RequestBody HashMap<String, String> foodObject) {
		User user = getUser(Integer.parseInt(foodObject.get("userId")));
		String cartid=user.getCartId();
		Cart cart=cartRepo.findById(cartid).get();
		cart.setMenuId(Integer.parseInt(foodObject.get("menuId")));
		cartRepo.save(cart);			
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/setItem")
	public ResponseEntity<Integer> setitem(@RequestBody HashMap<String, String> itemsObject) {
		int userId=Integer.parseInt(itemsObject.get("userId"));
		User user=getUser(userId);
		String cartId=user.getCartId();
		int itemId=Integer.parseInt(itemsObject.get("itemId"));
		int eventId=Integer.parseInt(itemsObject.get("eventId"));
		EventItemMapper ei=eiRepo.getEventItemByEventItem(cartId, eventId, itemId);
		if(ei!=null)
			return new ResponseEntity<Integer>(0,HttpStatus.BAD_REQUEST);
		EventItemMapper eiMapper=new EventItemMapper();
		eiMapper.setCartId(cartId);
		eiMapper.setEventId(eventId);
		eiMapper.setItemId(itemId);
		eiRepo.save(eiMapper);	
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/getitemsincart")
	public ResponseEntity<List<EventItemDetails>> getItemsInCart(@RequestBody HashMap<String,String> map, Model model){

		List<EventItemMapper> eventItems = cartRepo.getItems(map.get("cartId"));
		List<EventItemDetails> itemdetails = new ArrayList<>();
		for(EventItemMapper itemid : eventItems)
		{	
			Items item=itemRepo.findById(itemid.getItemId()).get();
			Events event=eventRepo.findById(itemid.getEventId()).get();
			EventItemDetails itemDetail=new EventItemDetails();
			itemDetail.setItemDescription(item.getItemDescription());
			itemDetail.setItemId(item.getItemId());
			itemDetail.setItemImages(item.getItemImages());
			itemDetail.setItemName(item.getItemName());
			itemDetail.setItemPrice(item.getItemPrice());
			itemDetail.setItemType(item.getItemType());
			itemDetail.setEventId(event.getEventId());
			itemDetail.setEventName(event.getEventName());
			itemdetails.add(itemDetail);
		}
		return new ResponseEntity<List<EventItemDetails>>(itemdetails, HttpStatus.OK); 
	}
	
	@CrossOrigin
	@PostMapping("/geteventitemmapper")
	public ResponseEntity<List<EventItemMapper>> getEventItemMapperInCart(@RequestBody HashMap<String,String> map, Model model){
		List<EventItemMapper> eventItems = cartRepo.getItems(map.get("cartid"));
		return new ResponseEntity<List<EventItemMapper>>(eventItems, HttpStatus.OK); 
	}
	
	private int setItemInOiMapper(String orderId,int itemId,int eventId) {
		
		OrderItemMapper oimapper = new OrderItemMapper();
		oimapper.setItemId(itemId);
		oimapper.setOrderId(orderId);
		oimapper.setEventId(eventId);
		oiRepo.save(oimapper);	
		return 1;
	}
	
	
	@CrossOrigin
    @PostMapping("/getcartbyuser")
    public ResponseEntity<Cart> getCartByUser(@RequestBody HashMap<String,String> map) throws Exception{
		User user = userRepo.findById(Integer.parseInt(map.get("userid"))).get();
    	Cart cart = cartRepo.findById(user.getCartId()).get();
    	if(cart.getDelFlag()==0)
    		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    	else
    		throw new Exception("cart doesnt exist");
    }
	private double getCartCost(String cartId)
	{
		double price=0.0;
		Cart cart = cartRepo.findById(cartId).get();
		User user=userRepo.findUserByCartId(cart.getCartId());
		Venue venue=venueRepo.findById(cart.getVenueId()).get();
		Catering catering=catRepo.findById(cart.getMenuId()).get();
		price=price+venue.getVenuePrice()+catering.getPricePerPlate()*user.getNoOfGuest();
		List<EventItemMapper> items = cartRepo.getItems(cartId);
		for(EventItemMapper eItem :items)
		{
			Items item=itemRepo.findById(eItem.getItemId()).get();
			price+=item.getItemPrice();
		}
		return price;
	}
	
	@CrossOrigin
	@PostMapping("/removeItemFromCart")
	public ResponseEntity<Integer> deleteItemFromCart(@RequestBody HashMap<String,String> map)
	{ System.out.println("InRemove");
		User user=userRepo.findById(Integer.parseInt(map.get("userId"))).get();
    	EventItemMapper ei=eiRepo.getEventItemByEventItem(user.getCartId(), Integer.parseInt(map.get("eventId")),Integer.parseInt(map.get("itemId")));
		eiRepo.deleteById(ei.getEiMapperId());
    	return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/removeVenueFromCart")
	public ResponseEntity<Integer> deleteVenueFromCart(@RequestBody HashMap<String,String> map)
	{
		System.out.println("In remove venue");
		User user=userRepo.findById(Integer.parseInt(map.get("userId"))).get();
		Cart cart = cartRepo.findById(user.getCartId()).get();
		cart.setVenueId(-1);
		cartRepo.save(cart);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/removeCateringFromCart")
	public ResponseEntity<Integer> deleteCateringFromCart(@RequestBody HashMap<String,String> map)
	{
		System.out.println("In remove catering");
		User user=userRepo.findById(Integer.parseInt(map.get("userId"))).get();
		Cart cart = cartRepo.findById(user.getCartId()).get();
		cart.setMenuId(-1);
		cartRepo.save(cart);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@CrossOrigin
    @PostMapping("/checkoutcart")
    public ResponseEntity<String> deleteCart(@RequestBody HashMap<String,String> map, Model model) throws Exception{
    	if(cartRepo.getDelFlag(map.get("cartid"))==1){
            throw new Exception("cart doesn't exist");
        }
    	Cart cart = cartRepo.findById(map.get("cartid")).get();
 //  	cart.setDelFlag(1);
//    	cart.setVenueId(0);
//    	cart.setMenuId(0);
    	cartRepo.save(cart);
    	Date date=new Date();
    	String orderId=date.getTime()+cart.getCartId().toString();
    	orderId=orderId.substring(7);
    	System.out.println("Order+"+orderId);
    	Orders order = new Orders();
    	order.setOrderId(orderId);
    	order.setMenuId(cart.getMenuId());
    	order.setVenueId(cart.getVenueId());
    	order.setTotalPrice(User.totalPrice);//to be calculated
    	User user = userRepo.findUserByCartId(map.get("cartid"));
    	order.setUserId(user.getUserId());
    	order.setDelFlag(0);
    	order.setTotalPrice(getCartCost(cart.getCartId()));
//    	order.setItemsPurchased(items);
    	orderRepo.save(order);
    	//todo: add delete items from cart);
    	List<EventItemMapper> items = cartRepo.getItems(map.get("cartid"));
    	for(EventItemMapper item : items) {
    		setItemInOiMapper(orderId, item.getItemId(),item.getEventId() );
    		eiRepo.deleteById(item.getEiMapperId());
    	}
    	System.out.println(orderId+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	return new ResponseEntity<String>(orderId, HttpStatus.OK);
    }
	
}
