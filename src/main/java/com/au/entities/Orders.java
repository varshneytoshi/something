package com.au.entities;

import java.io.Serializable;
import java.util.HashSet;
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
	private int orderId;

	@Column(name="UserId")
	private int userId;
	
	@Column(name="VenueId")
	private int venueId;
	
	@Column(name="MenuId")
	private int menuId;
	
	//add lib for it @JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
	@JoinTable(name="order_item_mapper" , joinColumns = { @JoinColumn(name="OrderId")},inverseJoinColumns= {@JoinColumn(name="ItemId")})
	Set<Items> ItemsPurchased=new HashSet<Items>();


	@Column(name="TotalPrice")
	private double totalPrice;
	
	@Column(name="DelFlag")
	private char delFlag;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
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

	public Set<Items> getItemsPurchased() {
		return ItemsPurchased;
	}

	public void setItemsPurchased(Set<Items> itemsPurchased) {
		ItemsPurchased = itemsPurchased;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public char getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}


}
