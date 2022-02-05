package com.iset.bp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Historique implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Formation_H;
	private String theme_H;
	private String type_H;
	@Column(name="objectif_H",length=2000)
	private String objectif_H;
	private int accept_H;
	
	
	@ManyToOne
	@JoinColumn(name="id_User")
	private User user;
	
	
	public Historique() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Historique(Integer id_Formation_H, String theme_H, String type_H, String objectif_H, int accept_H,
			User user) {
		super();
		this.id_Formation_H = id_Formation_H;
		this.theme_H = theme_H;
		this.type_H = type_H;
		this.objectif_H = objectif_H;
		this.accept_H = accept_H;
		this.user = user;
	}


	public Integer getId_Formation_H() {
		return id_Formation_H;
	}


	public void setId_Formation_H(Integer id_Formation_H) {
		this.id_Formation_H = id_Formation_H;
	}


	public String getTheme_H() {
		return theme_H;
	}


	public void setTheme_H(String theme_H) {
		this.theme_H = theme_H;
	}


	public String getType_H() {
		return type_H;
	}


	public void setType_H(String type_H) {
		this.type_H = type_H;
	}


	public String getObjectif_H() {
		return objectif_H;
	}


	public void setObjectif_H(String objectif_H) {
		this.objectif_H = objectif_H;
	}


	public int getAccept_H() {
		return accept_H;
	}


	public void setAccept_H(int accept_H) {
		this.accept_H = accept_H;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	
}
