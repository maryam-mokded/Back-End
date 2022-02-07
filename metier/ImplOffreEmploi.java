package com.example.metier;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.example.dao.OffreEmploiRepository;
import com.example.entities.OffreEmploi;
import org.springframework.stereotype.Service;

@Service
public class ImplOffreEmploi implements InterOffreEmploiMetier{
	
	@Autowired
	private OffreEmploiRepository OffreRep;
	
	@Override
	public List<OffreEmploi> getOffres(){
		return OffreRep.findAll();
	}
	
	@Override
	public OffreEmploi getOffre( long code) {
		Optional<OffreEmploi> offre = OffreRep.findById(code);
		if (offre.isPresent()) { 
			return offre.get();
		}else throw new RuntimeException("Offre introuvable");
		
	}
	

	@Override
	public void deleteOffre(long code) {
		Optional<OffreEmploi> offre = OffreRep.findById(code);
		if (offre.isPresent()) { 
			OffreRep.deleteById(code);
	    }else throw new RuntimeException("Offre introuvable on ne peut pas le supprimer");
	}
	
     
   @Override
   public OffreEmploi AddOffre(OffreEmploi offrAdd){
    	Optional<OffreEmploi> offre = OffreRep.findById(offrAdd.getIdOffre());
		if (offre.isPresent() == false) { 
			return OffreRep.save(offrAdd);
		}else throw new RuntimeException("Cette offre déjà existe");
	
	}
 
    
    @Override
    public OffreEmploi EditOffre( long code, OffreEmploi offreModif)throws ResourceNotFoundException{
    		
    	OffreEmploi offre = OffreRep.findById(code).orElseThrow(()->new ResourceNotFoundException("l'offre du code " +code + "n'existe pas"));
    	
    	offre.setIdOffre(offreModif.getIdOffre());
    	offre.setTitre(offreModif.getTitre());
    	offre.setNbPost(offreModif.getNbPost());
    	offre.setDescription(offreModif.getDescription());
    	offre.setDate(offreModif.getDate());
    	final OffreEmploi updatedOffre =  OffreRep.save(offre);
			
	  	return updatedOffre;
    }


}
