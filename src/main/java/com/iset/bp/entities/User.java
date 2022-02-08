package com.iset.bp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_user",discriminatorType=DiscriminatorType.STRING,length=15)
public class User implements Serializable,UserDetails {
		
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id_User;
		private String nom;
		private String prenom;
		private String email;
		private String adresse;
		private String niveau;
		private int Cin;
		private int tel;
		private String profession;
		private String matricule;
		private Date dateEmbauche;
		private Date dateNaissance;
		private String photo;
		private String username;
		private String password;
		private boolean enabled;
		private int pilote;
		private int Chef_Service;
		
		
		@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<Role>();
		
		@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		@JsonIgnore
		private Set<Notification> notifications;
		
		@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		@JsonIgnore
		private Set<Formation> formations;
		
		@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		@JsonIgnore
		private Set<Historique> historiques;

		
		@ManyToOne
		@JoinColumn(name="id_Direction")
		private Direction direction;
		
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public User(Integer id_User, String nom, String prenom, String email, String adresse, String niveau, int cin,
			int tel, String profession, String matricule, Date dateEmbauche, Date dateNaissance, String photo,
			String username, String password, boolean enabled, int pilote, Set<Role> roles,
			Set<Notification> notifications, Set<Formation> formations, Set<Historique> historiques,Direction direction) {
		super();
		this.id_User = id_User;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.niveau = niveau;
		this.Cin = cin;
		this.tel = tel;
		this.profession = profession;
		this.matricule = matricule;
		this.dateEmbauche = dateEmbauche;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.pilote = pilote;
		this.roles = roles;
		this.notifications = notifications;
		this.formations = formations;
		this.historiques = historiques;
		this.direction = direction;
	}


	public Integer getId_User() {
		return id_User;
	}


	public void setId_User(Integer id_User) {
		this.id_User = id_User;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getNiveau() {
		return niveau;
	}


	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}


	public int getCin() {
		return Cin;
	}


	public void setCin(int cin) {
		Cin = cin;
	}


	public int getTel() {
		return tel;
	}


	public void setTel(int tel) {
		this.tel = tel;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public Date getDateEmbauche() {
		return dateEmbauche;
	}


	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	

	public Set<Historique> getHistoriques() {
		return historiques;
	}


	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
   }


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getPilote() {
		return pilote;
	}


	public void setPilote(int pilote) {
		this.pilote = pilote;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}


	public Set<Formation> getFormations() {
		return formations;
	}


	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public int getChef_Service() {
		return Chef_Service;
	}

	public void setChef_Service(int chef_Service) {
		Chef_Service = chef_Service;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
	return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
	return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
	return false;
	}
	
	@Override
	public boolean isEnabled() {
	return false;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = this.getRoles();           
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		            
		            for (Role role : roles) {
		                authorities.add(new SimpleGrantedAuthority(role.getName()));
		            }
		            
		            return authorities;
	}
	
}	