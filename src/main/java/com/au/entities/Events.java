package com.au.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Events implements Serializable {

private static final long serialVersionUID = -2054386655979281129L;
	
	@Id
	@Column(name="EventId", unique=true, nullable=false)
	private int eventId;
	
	@Column(name="EventName")
	private int eventName;
	
	@Column(name="CultureId")
	private int cultureId;
	
	@Column(name="DelFlag")
	private char delFlag;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getEventName() {
		return eventName;
	}

	public void setEventName(int eventName) {
		this.eventName = eventName;
	}

	public int getCultureId() {
		return cultureId;
	}

	public void setCultureId(int cultureId) {
		this.cultureId = cultureId;
	}

	public char getDelFlag() {
		return delFlag;
	}

	public Events() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Events(int eventId, int eventName, int cultureId, char delFlag) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.cultureId = cultureId;
		this.delFlag = delFlag;
	}

	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}