package com.iset.bp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.ContactRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Contact;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ContactController {

	@Autowired
	ContactRepository contactRep;
	
	@Autowired
	UserRepository userRep;
	
	
	@GetMapping("/contacts")
	public List<Contact> AfficherContacts(){
		return contactRep.findAll();
	}
	
	@GetMapping("/contacts/{id}")
	public Optional<Contact> AfficherContact(@PathVariable Integer id) {
		return contactRep.findById(id);
	}
	
	@DeleteMapping("/contacts/{id}")
	public void SupprimerContact(@PathVariable Integer id) {
			contactRep.deleteById(id);
	}
	
	@RequestMapping(value="/contacts",method = RequestMethod.POST)
	public void AjouterContact(@RequestBody Contact contact){
		Optional<Contact> c =  contactRep.findById(contact.getId_Contact());
		if (c.isPresent() == false) { 
			userRep.save(contact.getUser());
			contactRep.save(contact);
		}else throw new RuntimeException("ce Contact déjà existe");

	}
	
	@PutMapping("/contacts")
	public void ModifierContact(@RequestBody Contact contact){
		Contact c = contactRep.findById(contact.getId_Contact()).orElseThrow(()->new ResourceNotFoundException("Ce contact n'existe pas"));
		c.setId_Contact(contact.getId_Contact());
		c.setMessage(contact.getMessage());
		c.setDate(contact.getDate());
		c.setUser(contact.getUser());
		contactRep.save(c);
    }

}
