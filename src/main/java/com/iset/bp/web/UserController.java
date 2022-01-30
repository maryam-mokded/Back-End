package com.iset.bp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.User;
import com.iset.bp.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserService userSer;
	
	@GetMapping("/users")
	public List <User> getusers(){
		return  userRep.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Optional<User> getUser(@PathVariable Integer id) {
		return userRep.findById(id);
	}
	
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable Integer id) {
			userRep.deleteById(id);
	}
	
	@RequestMapping(value="/users",method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public void AddUser(@RequestBody User user){
		Optional<User> u =  userRep.findById(user.getUserId());
		if (u.isPresent() == false) { 
			userRep.save(user);
		}else throw new RuntimeException("cet utilisateur déjà existe");

	}
	
	@PutMapping("/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void EditUser(@RequestBody User user){
		User u = userRep.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("Cet utilisateur n'existe pas"));
		u.setUserId(user.getUserId());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setAdress(user.getAdress());
		u.setCin(user.getCin());
		u.setEmail(user.getEmail());
		u.setTel(user.getTel());
		u.setUsername(user.getUsername());
		u.setPhoto(user.getPhoto());
		userRep.save(u);
	
    }

/*	@PutMapping("/users/{id}")
 	@PreAuthorize("hasAuthority('ADMIN')")
	public void CahnegerPassword(@PathVariable Integer id,@RequestBody ChangerPassword Changerpassword){
		User u = userRep.findById(id).orElseThrow(()->new ResourceNotFoundException("Cet utilisateur n'existe pas"));
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(Changerpassword.getOldpassword());
		
		if(u.getPassword() == hashedPassword ) {
			if(Changerpassword.getNewpassword() == Changerpassword.getConfirmedPassword() ) {
				PasswordEncoder NewpasswordEncoder = new BCryptPasswordEncoder();
				String NewhashedPassword = passwordEncoder.encode(Changerpassword.getNewpassword());
				u.setPassword(NewhashedPassword);
				userRep.save(u);
			}else {
				System.out.println("New Password / Confirmed Password incorrect");
			}
		}else{
			System.out.println("Old Password / New Password incorrect");
		}
				
	}*/
 	
}


class ChangerPassword{
	
	 private String Oldpassword;
	 private String Newpassword;
	 private String confirmedPassword;
	
	 public ChangerPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChangerPassword(String oldpassword, String newpassword, String confirmedPassword) {
		super();
		Oldpassword = oldpassword;
		Newpassword = newpassword;
		this.confirmedPassword = confirmedPassword;
	}

	public String getOldpassword() {
		return Oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		Oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return Newpassword;
	}

	public void setNewpassword(String newpassword) {
		Newpassword = newpassword;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	
	 
	 
}