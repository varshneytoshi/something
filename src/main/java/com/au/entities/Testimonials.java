package com.au.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Testimonials")
public class Testimonials implements Serializable{

private static final long serialVersionUID = -2054386655979281129L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="TestimonialsId")
private int testimonialsId;

@Column(name="UserId")
private int userId;

@Column(name="UserName")
private String userName;

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

@Column(name="Content")
private String content;

public int getTestimonialsId() {
	return testimonialsId;
}

public void setTestimonialsId(int testimonialsId) {
	this.testimonialsId = testimonialsId;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}