package com.example.metier;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.example.dao.CVRepository;
import com.example.entities.Cv;

@Service
public class ImplCV implements InterCVMetier{
	
	@Autowired
	private CVRepository CvRep;

	@Override
	public Cv getOneCv(long code) {
		Optional<Cv> cv = CvRep.findById(code);
		if (cv.isPresent()) { 
			return cv.get();
		}else throw new RuntimeException("CV introuvable");
	}

	@Override
	public List<Cv> getCv() {
		return CvRep.findAll();
	}

	@Override
	public void deleteCv(long code) {
		Optional<Cv> cv = CvRep.findById(code);
		if (cv.isPresent()) { 
			CvRep.deleteById(code);
	    }else throw new RuntimeException("CV introuvable on ne peut pas le supprimer");
	}

	@Override
	public Cv AddCv(Cv CvAdd) {
		Optional<Cv> cv = CvRep.findById(CvAdd.getIdCV());
		if (cv.isPresent() == false) { 
			return CvRep.save(CvAdd);
		}else throw new RuntimeException("Cette offre déjà existe");
	}

	@Override
	public Cv EditCv(long code, Cv CvModif) {
	Cv cv = CvRep.findById(code).orElseThrow(()->new ResourceNotFoundException("Ce CV n'existe pas"));
    	
    	cv.setIdCV(CvModif.getIdCV());
    	cv.setPdf(CvModif.getPdf());
    	
		CvRep.save(cv);
			
	  	return cv;
	}



}
