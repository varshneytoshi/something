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
@Table(name="eventts")
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
}