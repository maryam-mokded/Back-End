package com.iset.bp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iset.bp.DAO.DirectionRepository;
import com.iset.bp.entities.Direction;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DirectionController {

	@Autowired
	DirectionRepository directionRep;
	
	
	@GetMapping("/directions")
	//@PreAuthorize("hasAuthority('CHEF_SERVICE')")
	   public List<Direction> AfficherDirections(){
		return directionRep.findAll();
	}
	
	@GetMapping("/directions/{id}")
	//@PreAuthorize("hasAuthority('CHEF_SERVICE')")
	public Optional<Direction> AfficherDirection(@PathVariable Integer id) {
		return directionRep.findById(id);
	}
	
	@DeleteMapping("/directions/{id}")
	//@PreAuthorize("hasAuthority('CHEF_SERVICE')")
	public void SupprimerDirection(@PathVariable int id) {
		directionRep.deleteById(id);
	}
	
	@PostMapping("/directions")
	//@PreAuthorize("hasAuthority('CHEF_SERVICE')")
	public void AjouterDirection(@RequestBody Direction direction){
		Optional<Direction> d = directionRep.findById(direction.getId_Direction());
		if (d.isPresent() == false) { 
			directionRep.save(direction);
		}else throw new RuntimeException("cet direction déjà existe");

	}
	
}
