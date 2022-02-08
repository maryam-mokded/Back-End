package com.iset.bp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Notification implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Notification;
	@Column(name="message",length=2000)
	private String message;
	private Date date;
	private int id_D;
	

	@ManyToOne
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
	
	public int getId_D() {
		return id_D;
	}
	public void setId_D(int id_D) {
		this.id_D = id_D;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
