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


import com.iset.bp.DAO.FormationRepository;
import com.iset.bp.DAO.HistoriqueRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Formation;
import com.iset.bp.entities.Historique;
import com.iset.bp.entities.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HistoriqueController {

	@Autowired
	HistoriqueRepository historiqueRep;
	
	@Autowired
	FormationRepository formationRep;

	@Autowired
	UserRepository userRep;

	@Autowired
	FormationController formationCtr;

	@GetMapping("/historique/direction/{id}")
	public List<Historique> AfficherAllHistorique(@PathVariable Integer id){
		return historiqueRep.GroupageHistorique(id);
	}
	
	@GetMapping("/historique/{id}")
	public Optional<Historique> AfficherOneHistorique(@PathVariable Integer id) {
		return historiqueRep.findById(id);
	}
	
	@PostMapping("/historique/Accept/{id}")
	public void AjouterHistoriqueAccept(@PathVariable int id){	
	  	Formation formation = formationRep.findById(id).get();
	  	User user = userRep.findById(formation.getUser().getId_User()).get();
	  	Historique historique = new Historique();
	  	historique.setTheme_H(formation.getTheme());
	  	historique.setType_H(formation.getType());
	  	historique.setObjectif_H(formation.getObjectif());
	  	historique.setUser(user);
	  	historique.setAccept_H(1);
	  	historiqueRep.save(historique);
	  	formationCtr.SupprimerFormation(formation.getId_Formation());
	}
	
	
	@PostMapping("/historique/Refuse/{id}")
	public void AjouterHistoriqueRefuse(@PathVariable int id){	
	  	Formation formation = formationRep.findById(id).get();
	  	User user = userRep.findById(formation.getUser().getId_User()).get();
	  	Historique historique = new Historique();
	  	historique.setTheme_H(formation.getTheme());
	  	historique.setType_H(formation.getType());
	  	historique.setObjectif_H(formation.getObjectif());
	  	historique.setUser(user);
	  	historique.setAccept_H(0);
	  	historiqueRep.save(historique);
	  	formationCtr.SupprimerFormation(formation.getId_Formation());
	}
	
}

