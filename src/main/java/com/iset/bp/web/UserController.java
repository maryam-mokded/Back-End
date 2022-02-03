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
	public List<User> getusers(){
		return  userRep.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable Integer id) {
		return userRep.findById(id);
	}
	
	@GetMapping("/users/pilote/{id}")
	public User getPilote(@PathVariable int id) {
		List<User> userList = this.getusers();
		User pilote = new User();
		for ( int i=0;i<=userList.size();i++) {
			if(userList.get(i).getDirection().getId_Direction() == id ) {
				pilote = userList.get(i);
			}
		}
		return pilote;
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userRep.findById(id).get();
		user.setNotifications(null);
		user.setFormations(null);
		user.setDirection(null);
		userRep.deleteById(user.getId_User());
	}
	
	@RequestMapping(value="/users",method = RequestMethod.POST)
	public void AddUser(@RequestBody User user){
		Optional<User> u =  userRep.findById(user.getId_User());
		if (u.isPresent() == false) { 
			userRep.save(user);
		}else throw new RuntimeException("cet utilisateur déjà existe");

	}
	
	
	
	@PutMapping("/users")
	public void EditUser(@RequestBody User user){
		User u = userRep.findById(user.getId_User()).orElseThrow(()->new ResourceNotFoundException("Cet utilisateur n'existe pas"));
		u.setId_User(user.getId_User());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setAdresse(user.getAdresse());
		u.setCin(user.getCin());
		u.setEmail(user.getEmail());
		u.setTel(user.getTel());
		u.setPhoto(user.getPhoto());
		userRep.save(u);
    }

	 
}