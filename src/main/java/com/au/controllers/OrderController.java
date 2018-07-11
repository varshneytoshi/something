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

import com.au.entities.EventItemMapper;
import com.au.entities.OrderItemMapper;
import com.au.entities.Orders;
import com.au.entities.User;
import com.au.repositories.OrderItemRepository;
import com.au.repositories.OrderRepository;
import com.au.repositories.UserRepository;

@Controller
public class OrderController {
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	OrderItemRepository oiRepo;
	@Autowired
	UserRepository userRepo;
	
	@CrossOrigin
	@PostMapping("/getOrders")
	public ResponseEntity<List<Orders>> getArticleById(@RequestBody HashMap<String,String> map) {
			List<Orders> orders = orderRepo.findOrderByUserId(Integer.parseInt(map.get("userid")));
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}
	
	@CrossOrigin
    @PostMapping("/deleteorder")
    public ResponseEntity<Integer> deleteOrder(@RequestBody HashMap<String,String> map) throws Exception{
    	
    	Orders order = orderRepo.getOrders(map.get("orderid"));
    	if(order.getDelFlag()==1){
            throw new Exception("order doesn't exist");
        }
    	order.setDelFlag(1);
    	orderRepo.save(order);
    	return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
	
//	@CrossOrigin
//	@PostMapping("/addToOrderItem")
//	public void addOrderItem(@RequestBody HashMap<String,String> map){
//		orderRepo.insertOrderItem(Integer.parseInt(map.get("orderid")), Integer.parseInt(map.get("itemid")));
//	}
}
