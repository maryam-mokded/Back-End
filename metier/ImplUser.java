package com.example.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.UserRepository;
import com.example.entities.Condidats;
import com.example.entities.User;

@Service
public class ImplUser implements InterUserMetier {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUser() {
		List<User> user = new ArrayList<User>();
		user = userRepository.findAll();
		
		for (int i = 0 ; i <user.size();i++) {
			if (user.get(i) instanceof Condidats) {
				user.remove(user.get(i));
			}
		}
		 return user;
	}
	
	
	@Override
	public User getOneUser(long iduser) {
		Optional<User> user = userRepository.findById(iduser);
		if (user.isPresent()) { 
			return user.get();
		}else throw new RuntimeException("Utilisateur introuvable !! ");
	}

	

	
	
	@Override
	public void deleteUser(long idUser) {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isPresent()) { 
			userRepository.deleteById(idUser);
	    }else throw new RuntimeException("Utilisateur introuvable on ne peut pas le supprimer !");
	}


	@Override
	public User AddUser(User user) {
		Optional<User> u =  userRepository.findById(user.getIdUser());
		if (u.isPresent() == false) { 
			return userRepository.save(user);
		}else throw new RuntimeException("cet utilisateur déjà existe");
	}


	@Override
	public User EditUser(long id, User user) {
		User u = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cet utilisateur n'existe pas"));
			u.setIdUser(user.getIdUser());
			u.setNom(user.getNom());
			u.setPrenom(user.getPrenom());
			u.setAdress(user.getAdress());
			u.setCin(user.getCin());
			u.setEmail(user.getEmail());
			u.setTel(user.getTel());
			u.setPhoto(user.getPhoto());
			u.setLienGithub(user.getLienGithub());
			u.setLienFacebook(user.getLienFacebook());
			u.setLienInstagram(user.getLienInstagram());
			u.setLienLinkedIn(user.getLienLinkedIn());
			userRepository.save(u);
			
	  	return u;
	}

	
}
