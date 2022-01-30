package com.iset.bp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_user",discriminatorType=DiscriminatorType.STRING,length=15)
public class User implements Serializable , UserDetails {
		
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name= "user_id")
		private Integer userId;
		private String Nom;
		private String Prenom;
		private String Email;
		private String Adress;
		private int Cin;
		private int tel;
		private Date DateEmbauche;
		private String photo;
		private String username;
		private String password;
		private boolean enabled;

		@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	    @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<Role>();
		
		@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
		@JsonIgnore
		private Set<Contact> contact;
		
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer userId,String nom,String prenom,String email) {
		this.userId = userId;
		this.Nom = nom;
		this.Prenom = prenom;
		this.Email = email;
	}
	public User(Integer userId, String username, String password,String Adresse,String nom,String prenom,
			int Cin , int Tel,String Email,String photo) {
		super();
		this.userId = userId;
		this.Nom = nom;
		this.Prenom = prenom;
		this.DateEmbauche = new Date();
		this.Adress = Adresse;
		this.Cin = Cin;
		this.tel = Tel;
		this.photo = photo;
		this.Email =Email;
		this.username = username;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		this.password = hashedPassword;
		
	}


	public User(Integer userId, String username,String password,boolean enable,Set<Role> roles,String Adresse,String nom,String prenom,
			int Cin , int Tel,String Email,String photo) {
		super();
		this.userId = userId;
		this.Nom = nom;
		this.Prenom = prenom;
		this.DateEmbauche = new Date();
		this.Adress = Adresse;
		this.Cin = Cin;
		this.tel = Tel;
		this.photo = photo;
		this.Email =Email;
		this.username = username;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		this.password = hashedPassword;
		this.enabled = enable;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public String getPrenom() {
		return Prenom;
	}


	public void setPrenom(String prenom) {
		Prenom = prenom;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getAdress() {
		return Adress;
	}


	public void setAdress(String adress) {
		Adress = adress;
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


	public Date getDateEmbauche() {
		return DateEmbauche;
	}


	public void setDateEmbauche(Date dateEmbauche) {
		DateEmbauche = dateEmbauche;
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
	    //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(password);
		this.password = password;
	}
   
	
	
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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