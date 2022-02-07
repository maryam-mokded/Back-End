package com.example.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ContactRepository;
import com.example.entities.Contact;

@Service
public class ImplContact  implements InterContactMetier {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getContact() {
		List<Contact> contact = new ArrayList<Contact>();
		return contact = contactRepository.findAll();

	}

	@Override
	public Contact getOneContact(long id_Contact) {
		Optional<Contact> contact = contactRepository.findById(id_Contact);
		if (contact.isPresent()) { 
			return contact.get();
		}else throw new RuntimeException("Contact introuvable !! ");
	}

	@Override
	public void deleteContact(long id_Contact) {
		Optional<Contact> contact = contactRepository.findById(id_Contact);
		if (contact.isPresent()) { 
			contactRepository.deleteById(id_Contact);
	    }else throw new RuntimeException("Contact introuvable on ne peut pas le supprimer !");
	}


	@Override
	public Contact AddContact(Contact contact) {
		Optional<Contact> c =  contactRepository.findById(contact.getId_Contact());
		if (c.isPresent() == false) { 
			return contactRepository.save(contact);
		}else throw new RuntimeException("can't add contact ");
	}


}