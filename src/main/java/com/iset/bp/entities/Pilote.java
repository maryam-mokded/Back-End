package com.iset.bp.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;


@Entity
public class Pilote extends User{

	public Pilote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pilote(Integer id_User, String nom, String prenom, String email, String adresse, String niveau, int cin,
			int tel, String profession, String matricule, Date dateEmbauche, Date dateNaissance, String photo,
			String username, String password, boolean enabled, int pilote, Set<Role> roles,
			Set<Notification> notifications, Set<Formation> formations, Direction direction) {
		super(id_User, nom, prenom, email, adresse, niveau, cin, tel, profession, matricule, dateEmbauche, dateNaissance, photo,
				username, password, enabled, pilote, roles, notifications, formations, direction);
		// TODO Auto-generated constructor stub
	}

	
}
