package com.au.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User  implements Serializable {

private static final long serialVersionUID = -2054386655979281969L;
	
	public User() {
}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userId;
		
	@Column(name="UserName")
	private String userName;
	
	@Column(name="UserPass")
	private String userPass;
	
	@Column(name="UsermailId")
	private String UsermailId;
	
	@Column(name="UserContact")
	private String userContact;
	
	@Column(name="EstBudget")
	private double estBudget;
	
	@Column(name="NoOfGuest")
	private double noOfGuest;

	@Column(name="WeddingDate")
	private Date weddingDate;
	
	@OneToOne(fetch = FetchType.EAGER ,mappedBy="user")
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	private Cart cart;
	
	@Column(name="Culture")
	private int culture;

	@Column(name="NoOfWeddingDays")
	private double noOfWeddingDays;

	@Column(name="DelFlag")
	private boolean delFlag;

	
//	@OneToMany(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
//	@JoinTable(name="cust_coupon" , joinColumns = { @JoinColumn(name="custId")},inverseJoinColumns= {@JoinColumn(name="couponId")})
//	private Set<Coupon> coupons = new HashSet<Coupon>();
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getCulture() {
		return culture;
	}

	public void setCulture(int culture) {
		this.culture = culture;
	}

	public double getNoOfWeddingDays() {
		return noOfWeddingDays;
	}

	public void setNoOfWeddingDays(double noOfWeddingDays) {
		this.noOfWeddingDays = noOfWeddingDays;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUsermailId() {
		return UsermailId;
	}

	public void setUsermailId(String usermailId) {
		UsermailId = usermailId;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public double getEstBudget() {
		return estBudget;
	}

	public void setEstBudget(double estBudget) {
		this.estBudget = estBudget;
	}

	public double getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(double noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public Date getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(Date weddingDate) {
		this.weddingDate = weddingDate;
	}

//	@OneToOne(fetch = FetchType.EAGER ,mappedBy="user")
//	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
//	private Cart cart;
//	
	

}