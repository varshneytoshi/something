package com.au.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="user")
public class User  implements Serializable {
public User(int userId, String userName, String userPass, String usermailId, String userContact, double estBudget,
			int noOfGuest, Date weddingDate, int cartId, int culture, double noOfWeddingDays, int delFlag) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
		UsermailId = usermailId;
		this.userContact = userContact;
		this.estBudget = estBudget;
		this.noOfGuest = noOfGuest;
		this.weddingDate = weddingDate;
		this.cartId = cartId;
		this.culture = culture;
		this.noOfWeddingDays = noOfWeddingDays;
		this.delFlag = delFlag;
	}

public static double totalPrice = 0;
private static final long serialVersionUID = -2054386655979281969L;
	
	public static double getTotalPrice() {
	return totalPrice;
}

public static void setTotalPrice(double totalPrice) {
	User.totalPrice = totalPrice;
}

public int getDelFlag() {
	return delFlag;
}

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
	private int noOfGuest;

	@Column(name="WeddingDate")
	private Date weddingDate;
	
	@Column(name="CartId")
	private int cartId;
	
	@Column(name="Culture")
	private int culture;

	@Column(name="NoOfWeddingDays")
	private double noOfWeddingDays;

	@Column(name="DelFlag")
	private int delFlag;

	
//	@OneToMany(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.ALL)
//	@JoinTable(name="cust_coupon" , joinColumns = { @JoinColumn(name="custId")},inverseJoinColumns= {@JoinColumn(name="couponId")})
//	private Set<Coupon> coupons = new HashSet<Coupon>();

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

	public int isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
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

	public int getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(int noOfGuest) {
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
