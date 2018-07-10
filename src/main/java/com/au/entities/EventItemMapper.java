package com.au.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_item_mapper")
public class EventItemMapper {
	@Id
	@Column(name="EiMapperId", unique=true, nullable=false)
	private int eiMapperId;

	@Column(name="EventId")
	private int eventId;
	
	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name="ItemId")
	private int itemId;
	
	@Column(name="CartId")
	private int cartId;
	
	@Column(name="DelFlag")
	private int delFlag;

	public int getEiMapperId() {
		return eiMapperId;
	}

	public void setEiMapperId(int eiMapperId) {
		this.eiMapperId = eiMapperId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
}
