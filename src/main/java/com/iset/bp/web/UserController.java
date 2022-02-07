package com.iset.bp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Direction;
import com.iset.bp.entities.Employee;
import com.iset.bp.entities.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	DirectionController directionCtr;
	
	@Autowired
	UserRepository userRep;
	
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
    	List<User> userList = userRep.findByDirection(id);
    	User pilote = new User();
    	for ( int i=0; i< userList.size();i++) {
			if(userList.get(i).getPilote() == 1 ) {
				return pilote = userList.get(i);
			}
		}
    	return null;
	}
	
	@GetMapping("/users/direction/{id}")
	public List<User> getUserDirection(@PathVariable int id) {
		List<User> userList = userRep.findByDirection(id);
		return userList;
	}
		 
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userRep.findById(id).get();
		/*user.setNotifications(null);
		user.setFormations(null);*/	
		user.setDirection(null);
		//userRep.deleteById(user.getId_User());
	}
	
	@PostMapping("/users/{id}")
	public void AjouterUser(@RequestBody Employee user,@PathVariable int id){	
	  	Direction d = directionCtr.AfficherDirection(id).get();
	  	user.setDirection(d);
	  	userRep.save(user);
	}
		
	
	@PutMapping("/users/{idD}/{idU}")
	public void EditUser(@RequestBody User user,@PathVariable int idU,@PathVariable int idD){
		User u = userRep.findById(idU).orElseThrow(()->new ResourceNotFoundException("Cet utilisateur n'existe pas"));
		Direction d = directionCtr.AfficherDirection(idD).get();
		u.setId_User(user.getId_User());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setAdresse(user.getAdresse());
		u.setCin(user.getCin());
		u.setEmail(user.getEmail());
		u.setTel(user.getTel());
		u.setPhoto(user.getPhoto());
		u.setDirection(d);
		userRep.save(u);
    }

	 
}