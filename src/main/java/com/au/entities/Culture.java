package com.au.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Culture")
public class Culture implements Serializable{

private static final long serialVersionUID = -2054386655979281129L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="CultureId")
private int cultureId;

@Column(name="CultureName")
private String cultureName;

@Column(name="Culture_Creation_Id")
private int culture_Creation_Id;

@Column(name="Culture_Creation_Date")
private Date culture_Creation_Date;

@Column(name="Culture_Modification_Id")
private int Culture_Modification_Id;

@Column(name="Culture_Modification_Date")
private Date culture_Modification_Date;

@Column(name="DelFlag")
private int delFlag;

public int getCultureId() {
	return cultureId;
}

public void setCultureId(int cultureId) {
	this.cultureId = cultureId;
}

public String getCultureName() {
	return cultureName;
}

public void setCultureName(String cultureName) {
	this.cultureName = cultureName;
}

public int getCulture_Creation_Id() {
	return culture_Creation_Id;
}

public void setCulture_Creation_Id(int culture_Creation_Id) {
	this.culture_Creation_Id = culture_Creation_Id;
}

public Date getCulture_Creation_Date() {
	return culture_Creation_Date;
}

public void setCulture_Creation_Date(Date culture_Creation_Date) {
	this.culture_Creation_Date = culture_Creation_Date;
}

public int getCulture_Modification_Id() {
	return Culture_Modification_Id;
}

public void setCulture_Modification_Id(int culture_Modification_Id) {
	Culture_Modification_Id = culture_Modification_Id;
}

public Date getCulture_Modification_Date() {
	return culture_Modification_Date;
}

public void setCulture_Modification_Date(Date culture_Modification_Date) {
	this.culture_Modification_Date = culture_Modification_Date;
}

public int isDelFlag() {
	return delFlag;
}

public void setDelFlag(int delFlag) {
	this.delFlag = delFlag;
}

	
}
