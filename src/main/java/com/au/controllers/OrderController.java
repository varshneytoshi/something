package com.au.controllers;

import java.util.ArrayList;
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
import com.au.entities.EventItemDetails;
import com.au.entities.EventItemMapper;
import com.au.entities.Events;
import com.au.entities.Items;
import com.au.entities.OrderDetailPojo;
import com.au.entities.OrderItemDetails;
import com.au.entities.OrderItemMapper;
import com.au.entities.Orders;
import com.au.entities.User;
import com.au.entities.Venue;
import com.au.repositories.CateringRepository;
import com.au.repositories.EventRepository;
import com.au.repositories.ItemRepository;
import com.au.repositories.OrderItemRepository;
import com.au.repositories.OrderRepository;
import com.au.repositories.UserRepository;
import com.au.repositories.VenueRepository;

@Controller
public class OrderController {
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderItemRepository oiRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	VenueRepository venueRepo;
	@Autowired
	CateringRepository catRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	EventRepository eventRepo;

	@CrossOrigin
	@PostMapping("/getOrderDetails")
	public ResponseEntity<OrderDetailPojo> getOrderByUserIdOrderId(@RequestBody HashMap<String, String> map) throws Exception {
		Orders orders = null;
		if (map != null) {
			try {
				if (map.containsKey("orderId")) {
					String oid =map.get("orderId");
					if (oid !=null) {
						
						orders = orderRepo.findById(oid).get();
						OrderDetailPojo orderDetailPojo=new OrderDetailPojo();
						
						
						if (orders!=null) {
							System.out.println("Fetched orders from database");
							User user=userRepo.findById(orders.getUserId()).get();
							Venue venue=venueRepo.findById(orders.getVenueId()).get();
							Catering catering=catRepo.findById(orders.getMenuId()).get();
							Double totalPrice=0.0;
							List<OrderItemMapper> eventItems = orderRepo.getItems(orders.getOrderId());
							List<OrderItemDetails> itemdetails = new ArrayList<>();
							for(OrderItemMapper itemid : eventItems)
							{	
								Items item=itemRepo.findById(itemid.getItemId()).get();
								Events event=eventRepo.findById(itemid.getEventId()).get();
								
								OrderItemDetails oiDetail=new OrderItemDetails();
								oiDetail.setItemDescription(item.getItemDescription());
								oiDetail.setItemId(item.getItemId());
								oiDetail.setItemImages(item.getItemImages());
								oiDetail.setItemName(item.getItemName());
								oiDetail.setItemPrice(item.getItemPrice());
								oiDetail.setItemType(item.getItemType());
								oiDetail.setEventId(event.getEventId());
								oiDetail.setEventName(event.getEventName());
								totalPrice+=item.getItemPrice();
								itemdetails.add(oiDetail);
							}
							
							//set OrderDetailPojo values
							orderDetailPojo.setOrderId(orders.getOrderId());
							orderDetailPojo.setUserId(orders.getUserId());
							orderDetailPojo.setUserName(user.getUserName());
							orderDetailPojo.setMailId(user.getUsermailId());
							orderDetailPojo.setVenue(venue);
							orderDetailPojo.setMenu(catering);
							orderDetailPojo.setItems(itemdetails);
							orderDetailPojo.setCateringPrice(catering.getPricePerPlate()*user.getNoOfGuest()*user.getNoOfWeddingDays());
							
							totalPrice+=venue.getVenuePrice()+user.getNoOfGuest()*catering.getPricePerPlate()*user.getNoOfWeddingDays();
							orderDetailPojo.setTotalPrice(totalPrice);
							
							return new ResponseEntity<OrderDetailPojo>(orderDetailPojo, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<OrderDetailPojo>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("invalid user id");
						throw new Exception();
					}
				} else {
					System.out.println("empty user id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<OrderDetailPojo>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<OrderDetailPojo>(HttpStatus.BAD_REQUEST);
		}
	}

	
	
	@CrossOrigin
	@PostMapping("/getOrders")
	public ResponseEntity<List<Orders>> getOrderByUserId(@RequestBody HashMap<String, String> map) throws Exception {
		List<Orders> orders = null;
		if (map != null) {
			try {
				if (map.containsKey("userid")) {
					int uid = Integer.parseInt(map.get("userid"));
					if (uid > 0) {
						orders = orderRepo.findOrderByUserId(uid);
						if (orders.size() > 0) {
							System.out.println("Fetched orders from database");
							return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
						} else {
							System.out.println("Query returned empty set");
							return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("invalid user id");
						throw new Exception();
					}
				} else {
					System.out.println("empty user id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<Orders>>(HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<List<Orders>>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@PostMapping("/deleteorder")
	public ResponseEntity<Integer> deleteOrder(@RequestBody HashMap<String, String> map) throws Exception {
		if (map != null) {
			try {
				if (map.containsKey("orderid")) {
					Orders order = orderRepo.getOrders(map.get("orderid"));
					if (order != null) {
						if (order.getDelFlag() == 0) {
							System.out.println("Fetched orders object from database");
							order.setDelFlag(1);
							orderRepo.save(order);
							System.out.println("Orders object deleted from database");
							return new ResponseEntity<Integer>(1, HttpStatus.OK);
						}
						else {
							System.out.println("Query returned null");
							return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					} else {
						System.out.println("Query returned null");
						return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					System.out.println("Empty order id");
					throw new Exception();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
			}
			// @CrossOrigin
			// @PostMapping("/addToOrderItem")
			// public void addOrderItem(@RequestBody HashMap<String,String> map){
			// orderRepo.insertOrderItem(Integer.parseInt(map.get("orderid")),
			// Integer.parseInt(map.get("itemid")));
			// }
		} else {
			System.out.println("Request object is null");
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);

		}
	}
}