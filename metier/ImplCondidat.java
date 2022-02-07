package com.example.metier;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.CondidatRepository;
import com.example.dao.InterviewRepository;
import com.example.dao.OffreEmploiRepository;
import com.example.dao.UserRepository;
import com.example.entities.Condidats;
import com.example.entities.Interview;
import com.example.entities.OffreEmploi;
import com.example.entities.User;

@Service
public class ImplCondidat implements InterCondidatMetier {

	@Autowired
	OffreEmploiRepository offerRep;
	
	@Autowired
	InterviewRepository interviewRep;
	
	@Autowired
	private CondidatRepository CondRep;

	@Override
	public Condidats getOneCondidat(long iduser) {
		Optional<Condidats> condidats = CondRep.findById(iduser);
		if (condidats.isPresent()) { 
			return condidats.get();
		}else throw new RuntimeException("Condidat introuvable !! ");
	}

	@Override
	public List<Condidats> getCondidat() {
		return CondRep.findAll();
	}

	@Override
	public void deleteCondidat(long idUser) {
		Optional<Condidats> condidats = CondRep.findById(idUser);
		if (condidats.isPresent()) { 
			Condidats condidat = condidats.get();
			CondRep.delete(condidat);
		}else throw new RuntimeException("Condidat introuvable !! ");	
	}
	
	@Override
	public void AddCondidat(Condidats user,long idOffre) {
		List<Condidats> CondidatList = this.getCondidat();	
		Condidats CondAj = new Condidats();
		boolean Test = false;
		for(int i = 0 ;i<CondidatList.size();i++) {
			if(CondidatList.get(i).getCin() == user.getCin()) {
				Test = true;
				CondAj =CondidatList.get(i); 
			}else {
				Test = false;
			}
		}
		if (Test == false) { 
		  	OffreEmploi offer = offerRep.findById(idOffre).get();
		  	Interview interview = new Interview();
			interview.setUser(user);
			interview.setOffre(offer);
			Set<Interview> ListInterv =new HashSet<Interview>() ;
			ListInterv.add(interview);
			user.setInterview(ListInterv);
			CondRep.save(user);
    	}else {
    		OffreEmploi offer = offerRep.findById(idOffre).get();
		  	Interview interview = new Interview();
			interview.setUser(CondAj);
			interview.setOffre(offer);
			Set<Interview> ListInterv =new HashSet<Interview>() ;
			ListInterv.add(interview);
			CondAj.setInterview(ListInterv);
			CondRep.save(CondAj);
    		
    	}
	}
	 
	
	@Override
	public Condidats EditCondidat(long id, Condidats condidat) {
		Condidats c = CondRep.findById(id).orElseThrow(()->new ResourceNotFoundException("Ce condidat n'existe pas"));
		c.setIdUser(condidat.getIdUser());
		c.setNom(condidat.getNom());
		c.setPrenom(condidat.getPrenom());
		c.setAdress(condidat.getAdress());
		c.setCin(condidat.getCin());
		c.setEmail(condidat.getEmail());
		c.setTel(condidat.getTel());
		 CondRep.save(c);
		 return c;
	}

	
}
