package com.au.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Venue")
public class Venue implements Serializable {

	private static final long serialVersionUID = -2054386655979281129L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VenueId")
	private int venueId;

	@Column(name = "VenueName") // ,unique=true)
	private String venueName;

	@Column(name = "VenueType")
	private String venueType;

	@Column(name = "VenueLocation")
	private String venueLocation;

	@Column(name = "VenuePrice")
	private Double venuePrice;

	@Column(name = "VenueCapacity")
	private int venueCapacity;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDelFlag() {
		return delFlag;
	}

	@Column(name = "DelFlag")
	private int delFlag;

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public String getVenueLocation() {
		return venueLocation;
	}

	public void setVenueLocation(String venueLocation) {
		this.venueLocation = venueLocation;
	}

	public Double getVenuePrice() {
		return venuePrice;
	}

	public void setVenuePrice(Double venuePrice) {
		this.venuePrice = venuePrice;
	}

	public int getVenueCapacity() {
		return venueCapacity;
	}

	public void setVenueCapacity(int venueCapacity) {
		this.venueCapacity = venueCapacity;
	}

	public int isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

}
