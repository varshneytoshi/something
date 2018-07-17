package com.au.entities;

import java.util.List;

public class OrderDetailPojo {
	private String orderId;
	
	private int userId;
	
	private String userName;
	
	private String mailId;
	
	private Venue venue;
	
	private Catering menu;
	
	private List<OrderItemDetails> items;

	private double totalPrice;

	private double cateringPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Catering getMenu() {
		return menu;
	}

	public void setMenu(Catering menu) {
		this.menu = menu;
	}

	public List<OrderItemDetails> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDetails> items) {
		this.items = items;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public double getCateringPrice() {
		return cateringPrice;
	}

	public void setCateringPrice(double cateringPrice) {
		this.cateringPrice = cateringPrice;
	}
	
	
}
