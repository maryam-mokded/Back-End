package com.iset.bp.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.NotificationRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Formation;
import com.iset.bp.entities.Notification;
import com.iset.bp.entities.User;



@RestController
@CrossOrigin(origins="http://localhost:4200")
public class NotificationController {

	@Autowired
	NotificationRepository notificationRep;
	
	@Autowired
	UserRepository userRep;
	
	
	@GetMapping("/notifications/user/{id}")
	public List<Notification> AfficherNotificationsUser(@PathVariable Integer id){
		return notificationRep.GroupageNotification(id);
	}
	
	@GetMapping("/notifications/{id}")
	public Optional<Notification> AfficherNotification(@PathVariable Integer id) {
		return notificationRep.findById(id);
	}
	
	@DeleteMapping("/notifications/{id}")
	public void SupprimerNotification(@PathVariable int id) {
		 notificationRep.deleteById(id);
	}
	
	@PostMapping("/notifications/{id}")
	public void AjouterNotification(@PathVariable int id){
		User user = userRep.findById(id).get();
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setMessage("Cette notification de la part de chef de service formation");
		notification.setUser(user);
		notificationRep.save(notification);
	}


}