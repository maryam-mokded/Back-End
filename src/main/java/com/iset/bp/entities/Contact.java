package com.iset.bp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Contact implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Contact;
	private String message;
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer id_Contact, String message, Date date, User user) {
		super();
		this.id_Contact = id_Contact;
		this.message = message;
		this.date = date;
		this.user = user;
	}

	public Integer getId_Contact() {
		return id_Contact;
	}

	public void setId_Contact(Integer id_Contact) {
		this.id_Contact = id_Contact;
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
