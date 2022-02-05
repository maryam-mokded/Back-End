package com.iset.bp.web;

import java.util.ArrayList;
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

	@GetMapping("/formations/ServiceF/{id}")
	public List<Formation> AfficherFormationsService(@PathVariable Integer id){
		List<Formation> formations = formationRep.GroupageFormation(id);
		List<Formation> NewFormationsList = new ArrayList<Formation>();
		for (int i = 0; i < formations.size(); i++) {
	        if (formations.get(i).getValidate() == 1) {
	         	NewFormationsList.add(formations.get(i));
	         }
		}
		return NewFormationsList;
	}

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
	  	formation.setValidate(0);
	  	formationRep.save(formation);
	}
		
	@DeleteMapping("/formations/{id}")
	public void SupprimerFormation(@PathVariable int id) {	
		  Formation formation = formationRep.findById(id).get();
		  formation.setUser(null);
		  formationRep.deleteFormation(id);
	}
	
	@PutMapping("/formations/{idF}/{idU}")
	public void ModifierFormation(@RequestBody Formation formation,@PathVariable Integer idF,@PathVariable Integer idU){
		Formation f = formationRep.findById(idF).orElseThrow(()->new ResourceNotFoundException("Cet formation n'existe pas"));
		User u = userCtr.getUser(idU).get();
		f.setId_Formation(formation.getId_Formation());
		f.setTheme(formation.getTheme());
		f.setType(formation.getType());
		f.setObjectif(formation.getObjectif());
		f.setValidate(formation.getValidate());
		f.setUser(u);
		formationRep.save(f);
    }
	
	@PutMapping("/formations/envoyer/{id}")
	public void EnvoyerFormation(@PathVariable int id){
		Formation f = formationRep.findById(id).get();
		f.setValidate(1);
		formationRep.save(f);
    }

}

