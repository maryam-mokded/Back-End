package com.iset.bp.entities;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Direction implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Direction;
	private String Nom_Direction;
	
	/*
	@OneToMany(mappedBy="direction",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<User> users;
	*/
	
	public Direction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Direction(Integer id_Direction, String nom_Direction) {
		super();
		this.id_Direction = id_Direction;
		Nom_Direction = nom_Direction;
	}

	public Integer getId_Direction() {
		return id_Direction;
	}

	public void setId_Direction(Integer id_Direction) {
		this.id_Direction = id_Direction;
	}

	public String getNom_Direction() {
		return Nom_Direction;
	}

	public void setNom_Direction(String nom_Direction) {
		Nom_Direction = nom_Direction;
	}
	
	
	
}
