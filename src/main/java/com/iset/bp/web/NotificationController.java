package com.iset.bp.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.NotIdentifiableEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.DirectionRepository;
import com.iset.bp.DAO.NotificationRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Direction;
import com.iset.bp.entities.Notification;
import com.iset.bp.entities.User;



@RestController
@CrossOrigin(origins="http://localhost:4200")
public class NotificationController {

	@Autowired
	NotificationRepository notificationRep;

	@Autowired
	UserRepository userRep;
	
	@Autowired
	DirectionRepository directionRep;
	
	@Autowired
	UserController userCtr;
	
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
	
	@PostMapping("/notifications/envoyerP/{id}")
	public void AjouterNotification(@PathVariable int id){
		User user = userRep.findById(id).get();
		Date date = new Date();
		int jourD = date.getDate();
		int jourF = date.getDate() + 07;
		int mois = date.getMonth();
		int annee = date.getYear();
		String msg = user.getNom()+" "+ user.getPrenom()+ "Afin d'établir le plan de formation priére "
			  + "nous exprimer vos besoins en formation pour l'année  " + annee + 
				" et ce au plus tard le  "+ jourF + " / " + mois ;
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setMessage(msg);
		notification.setUser(user);
		notificationRep.save(notification);
	}
	
	
  @PostMapping("/notifications/{id}")
	public void AjouterNotificationAuServF(@PathVariable int id){
		User user = userCtr.getChefServiceFormation();
		Direction direction = directionRep.findById(id).get();
		String msg = "Monsieur / Madame  " + user.getNom()+ " " + user.getPrenom()+
				"  j'ai bien recu votre notification et cette liste comprend les formation les plus "
			  + " importantes pour les employés  de direction '"  + direction.getNom_Direction()
			  + "' Merci de vérifier la liste " ;
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setMessage(msg);
		notification.setId_D(id);
		notification.setUser(user);
		notificationRep.save(notification);
	}
	

	
    /*
	@PostMapping("/notifications/{id}")
	public void AjouterNotification(@PathVariable int id,@RequestBody Notification notif){
		User user = userRep.findById(id).get();
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setMessage(notif.getMessage());
		notification.setUser(user);
		notificationRep.save(notification);
	}
    */
	
	
}