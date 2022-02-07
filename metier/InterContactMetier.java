package com.example.metier;

import java.util.List;

import com.example.entities.Contact;

public interface InterContactMetier {

	public Contact getOneContact(long id_Contact);
	public List<Contact> getContact();
	public void deleteContact(long id_Contact);
	public Contact AddContact(Contact contact);

}
