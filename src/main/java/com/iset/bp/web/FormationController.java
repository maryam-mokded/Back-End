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
import com.iset.bp.entities.Formation;
import com.iset.bp.entities.User;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class FormationController {

	@Autowired
	FormationRepository formationRep;
	
	@Autowired
	UserController userCtr;

	@GetMapping("/formations/direction/{id}")
	public List<Formation> AfficherFormations(@PathVariable Integer id){
		return formationRep.GroupageFormation(id);
	}
	
	@GetMapping("/formations/{id}")
	public Optional<Formation> AfficherFormation(@PathVariable Integer id) {
		return formationRep.findById(id);
	}
	
	@PostMapping("/formations/{id}")
	public void AjouterFormation(@RequestBody Formation formation,@PathVariable int id){	
	  	User u = userCtr.getUser(id).get();
	  	formation.setUser(u);
	  	formationRep.save(formation);
	}
		
	@DeleteMapping("/formations/{id}")
	public void SupprimerFormation(@PathVariable int id) {	
		  Formation formation = formationRep.findById(id).get();
		  formation.setUser(null);
		  formationRep.deleteById(id);
	}
	
	@PutMapping("/formations/{id}")
	public void ModifierFormation(@RequestBody Formation formation,@PathVariable int id){
		Formation f = formationRep.findById(id).orElseThrow(()->new ResourceNotFoundException("Cet formation n'existe pas"));
		f.setId_Formation(formation.getId_Formation());
		f.setTheme(formation.getTheme());
		f.setType(formation.getType());
		f.setObjectif(formation.getObjectif());
		f.setAccept(formation.getAccept());
		f.setHistorique(formation.getHistorique());
		f.setUser(formation.getUser());
		formationRep.save(f);
    }
	

}

