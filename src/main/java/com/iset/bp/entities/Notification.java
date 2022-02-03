package com.iset.bp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Notification implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Notification;
	private String message;
	private Date date;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_User")
	private User user;
	
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(Integer id_Notification, String message, Date date) {
		super();
		this.id_Notification = id_Notification;
		this.message = message;
		this.date = date;
	}
	public Integer getId_Notification() {
		return id_Notification;
	}
	public void setId_Notification(Integer id_Notification) {
		this.id_Notification = id_Notification;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
