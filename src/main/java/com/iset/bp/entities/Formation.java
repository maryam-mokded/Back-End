package com.iset.bp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Formation implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Formation;
	private String theme;
	private String type;
	@Column(name="objectif",length=2000)
	private String objectif;
	private int validate;
	
	
	@ManyToOne
	@JoinColumn(name="id_User")
	private User user;
	
	
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Formation(Integer id_Formation, String theme, String type, String objectif) {
		super();
		this.id_Formation = id_Formation;
		this.theme = theme;
		this.type = type;
		this.objectif = objectif;
		this.validate = 0;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId_Formation() {
		return id_Formation;
	}

	public void setId_Formation(Integer id_Formation) {
		this.id_Formation = id_Formation;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public int getValidate() {
		return validate;
	}

	public void setValidate(int validate) {
		this.validate = validate;
	}
	
	
	
}
