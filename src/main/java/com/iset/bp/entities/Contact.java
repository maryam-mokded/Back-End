package com.iset.bp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Contact implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Contact;
	private String message;
	private Date date;
	private String email;
	private String nom;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer id_Contact, String message, Date date, String nom, String email) {
		super();
		this.id_Contact = id_Contact;
		this.message = message;
		this.date = date;
		this.email = email;
		this.nom = nom;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
