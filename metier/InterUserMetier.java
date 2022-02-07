package com.example.metier;

import java.util.List;

import com.example.entities.Condidats;
import com.example.entities.User;

public interface InterUserMetier {
	public User getOneUser(long iduser);
	public List<User> getUser();
	public void deleteUser(long idUser);
	public User AddUser(User user);
	public User EditUser(long id,User user);
	
	
	
}
