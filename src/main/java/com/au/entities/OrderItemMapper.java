package com.au.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_item_mapper")
public class OrderItemMapper {
	@Id
	@Column(name="OiMapperId", unique=true, nullable=false)
	private int oiMapperId;

	@Column(name="OrderId")
	private int orderId;
	
	@Column(name="ItemId")
	private int itemId;
	
	@Column(name="UserId")
	private int userId;

	public int getOiMapperId() {
		return oiMapperId;
	}

	public void setOiMapperId(int oiMapperId) {
		this.oiMapperId = oiMapperId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
