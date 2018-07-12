package com.au.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cart")
public class Cart  implements Serializable {

private static final long serialVersionUID = -2054386655979281129L;
	
	@Id
	@Column(name="CartId", unique=true, nullable=false)
	private String cartId;
	
	@Column(name="VenueId")
	private int venueId;
	
	@Column(name="MenuId")
	private int menuId;
	
//	//add lib for it @JsonIgnore
//	@ManyToMany(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
//	@JoinTable(name="cart_item_mapper" , joinColumns = { @JoinColumn(name="CartId")},inverseJoinColumns= {@JoinColumn(name="ItemId")})
//	Set<Items> Items=new HashSet<Items>();


	@Column(name="DelFlag")
	private int delFlag;


	public String getCartId() {
		return cartId;
	}


	public void setCartId(String cartId) {
		this.cartId = cartId;
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


//	public Set<Items> getItems() {
//		return Items;
//	}
//
//
//	public void setItems(Set<Items> items) {
//		Items = items;
//	}
//

	public int getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}
	
}
