package com.example.metier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.CondidatRepository;
import com.example.dao.InterviewRepository;
import com.example.entities.Condidats;
import com.example.entities.Interview;

@Service
public class InterviewMetierImpl  implements InterviewMetier {
	
	@Autowired
	private CondidatRepository condidatRep;
	
	@Autowired
	private InterviewRepository interviewRep;
	
	@Override
	public Interview getOneInterview(long id_Interview) {
		Optional<Interview> interview = interviewRep.findById(id_Interview);
		if (interview.isPresent()) { 
			return interview.get();
		}else throw new RuntimeException("Interview est indisponible");
	}

	@Override
	public List<Interview> getInterview() {
		return interviewRep.findAll();
	}

	@Override
	public void deleteInterview(long id_Interview) {
		Optional<Interview> interview = interviewRep.findById(id_Interview);
		if (interview.isPresent()) { 
		  Interview	interviewDeleted = interview.get();
		  interviewDeleted.setOffre(null);
		  Condidats condidat = condidatRep.findById(interviewDeleted.getUser().getIdUser()).get();
		  interviewDeleted.setUser(null);
		  condidatRep.deleteById(condidat.getIdUser());
		  //interviewRep.deleteById(interviewDeleted.getId_Interview());
		  interviewRep.deleteInterview(id_Interview);
	    }else throw new RuntimeException("can't delete interview");
	}

	@Override
	public Interview AddInterview(Interview interviewAdd) {
		Optional<Interview> interview = interviewRep.findById(interviewAdd.getId_Interview());
		if (interview.isPresent() == false) { 
			interviewAdd.setTest(0);
			return interviewRep.save(interviewAdd);
		}else throw new RuntimeException("you can't add a new interview");
	}

	@Override
	public Interview EditInterview(long id_Interview, Interview interviewUpdate) {
		Interview interview = interviewRep.findById(id_Interview).orElseThrow(()->new ResourceNotFoundException("can't find interview"));
    	
		interview.setId_Interview(interviewUpdate.getId_Interview());
		interview.setInterviewDate(interviewUpdate.getInterviewDate());
		interview.setInterviewType(interviewUpdate.getInterviewType());
		interview.setTest(interviewUpdate.getTest());
		interview.setTime(interviewUpdate.getTime());
		interview.setLocation(interviewUpdate.getLocation());
    	
    	interviewRep.save(interview);
			
	  	return interview;
	}

	
	
}
