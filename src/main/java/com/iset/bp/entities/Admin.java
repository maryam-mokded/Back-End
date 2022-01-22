package com.iset.bp.entities;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="admin")
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer userId, String username, String password, boolean enable, Set<Role> roles, String Adresse,
			String nom, String prenom, int Cin, int Tel, String Email, String photo) {
		super(userId, username, password, enable, roles, Adresse, nom, prenom, Cin, Tel, Email, photo);
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer userId, String username, String password, String Adresse, String nom, String prenom, int Cin,
			int Tel, String Email, String photo) {
		super(userId, username, password, Adresse, nom, prenom, Cin, Tel, Email, photo);
		// TODO Auto-generated constructor stub
	}

	
}
