package com.iset.bp.web;

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

import com.iset.bp.DAO.DirectionRepository;
import com.iset.bp.entities.Direction;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DirectionController {

	@Autowired
	DirectionRepository directionRep;
	
	
	@GetMapping("/directions")
	public List<Direction> AfficherDirections(){
		return directionRep.findAll();
	}
	
	@GetMapping("/directions/{id}")
	public Optional<Direction> AfficherDirection(@PathVariable Integer id) {
		return directionRep.findById(id);
	}
	
	@DeleteMapping("/directions/{id}")
	public void SupprimerDirection(@PathVariable int id) {
		
		directionRep.deleteById(id);
	}
	
	@RequestMapping(value="/directions",method = RequestMethod.POST)
	public void AjouterDirection(@RequestBody Direction direction){
		Optional<Direction> d = directionRep.findById(direction.getId_Direction());
		if (d.isPresent() == false) { 
			directionRep.save(direction);
		}else throw new RuntimeException("cet direction déjà existe");

	}
	
}
