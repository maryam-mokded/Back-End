package com.iset.bp.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.NotificationRepository;
import com.iset.bp.entities.Notification;



@RestController
@CrossOrigin(origins="http://localhost:4200")
public class NotificationController {

	@Autowired
	NotificationRepository notificationRep;
	
	
	@GetMapping("/notifications")
	public List<Notification> AfficherNotifications(){
		return notificationRep.findAll();
	}
	
	@GetMapping("/notifications/{id}")
	public Optional<Notification> AfficherNotification(@PathVariable Integer id) {
		return notificationRep.findById(id);
	}
	
	@DeleteMapping("/notifications/{id}")
	public void SupprimerNotification(@PathVariable int id) {
		 notificationRep.deleteById(id);
	}
	
	@RequestMapping(value="/notifications",method = RequestMethod.POST)
	public void AjouterNotification(@RequestBody Notification notification){
		Optional<Notification> n =  notificationRep.findById(notification.getId_Notification());
		if (n.isPresent() == false) { 
			notification.setDate(new Date());
			notificationRep.save(notification);
		}else throw new RuntimeException("cet Notification déjà existe");

	}

}
