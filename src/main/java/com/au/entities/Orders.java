package com.au.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Orders implements Serializable{
	private static final long serialVersionUID = -2054386655979281129L;
	
	@Id
	@Column(name="OrderId", unique=true, nullable=false)
	private String orderId;

	@Column(name="UserId")
	private int userId;
	
	@Column(name="VenueId")
	private int venueId;
	
	@Column(name="MenuId")
	private int menuId;
	
	//add lib for it @JsonIgnore
//	@ManyToMany(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
//	@JoinTable(name="order_item_mapper" , joinColumns = { @JoinColumn(name="OrderId")},inverseJoinColumns= {@JoinColumn(name="ItemId")})
//	List<Items> ItemsPurchased=new ArrayList<Items>();


	@Column(name="TotalPrice")
	private double totalPrice;
	
	@Column(name="DelFlag")
	private int delFlag;

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

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

//	public List<Items> getItemsPurchased() {
//		return ItemsPurchased;
//	}
//
//	public void setItemsPurchased(List<Items> itemsPurchased) {
//		ItemsPurchased = itemsPurchased;
//	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}


}
