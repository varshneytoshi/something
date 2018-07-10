package com.au.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Catering")
@EntityListeners(AuditingEntityListener.class)
public class Catering implements Serializable {

private static final long serialVersionUID = -2054386655979281129L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="MenuId")
private int menuId;

@Column(name="PackageName")//,unique=true)
private String packageName;

@Column(name="MenuDescription")
private String menuDescription;

@Column(name="PricePerPlate")
private double pricePerPlate;

@Column(name="CultureId")
private int cultureId;

//no of plates check it

@Column(name="PackageType")
private int packageType;

@Column(name="DelFlag")
private int delFlag;

public int getMenuId() {
	return menuId;
}

public void setMenuId(int menuId) {
	this.menuId = menuId;
}

public String getPackageName() {
	return packageName;
}

public void setPackageName(String packageName) {
	this.packageName = packageName;
}

public String getMenuDescription() {
	return menuDescription;
}

public void setMenuDescription(String menuDescription) {
	this.menuDescription = menuDescription;
}

public double getPricePerPlate() {
	return pricePerPlate;
}

public void setPricePerPlate(double pricePerPlate) {
	this.pricePerPlate = pricePerPlate;
}

public int getCultureId() {
	return cultureId;
}

public void setCultureId(int cultureId) {
	this.cultureId = cultureId;
}

public int getPackageType() {
	return packageType;
}

public void setPackageType(int packageType) {
	this.packageType = packageType;
}

public int isDelFlag() {
	return delFlag;
}

public void setDelFlag(int delFlag) {
	this.delFlag = delFlag;
}

}
