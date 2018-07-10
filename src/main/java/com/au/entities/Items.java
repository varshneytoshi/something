package com.au.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="items")
public class Items implements Serializable {

private static final long serialVersionUID = -2054386655979281129L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ItemId")
private int itemId;

@Column(name="ItemName")//,unique=true)
private String itemName;

@Column(name="ItemType")
private String itemType;

@Column(name="ItemDescription")
private String itemDescription;

@Column(name="ItemPrice")
private Double itemPrice;

@Column(name="ItemImages")
private String itemImages;

@Column(name="DelFlag")
private boolean delFlag;

public int getItemId() {
	return itemId;
}

public void setItemId(int itemId) {
	this.itemId = itemId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getItemType() {
	return itemType;
}

public void setItemType(String itemType) {
	this.itemType = itemType;
}

public String getItemDescription() {
	return itemDescription;
}

public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
}

public Double getItemPrice() {
	return itemPrice;
}

public void setItemPrice(Double itemPrice) {
	this.itemPrice = itemPrice;
}

public String getItemImages() {
	return itemImages;
}

public void setItemImages(String itemImages) {
	this.itemImages = itemImages;
}

public boolean isDelFlag() {
	return delFlag;
}

public void setDelFlag(boolean delFlag) {
	this.delFlag = delFlag;
}


}
