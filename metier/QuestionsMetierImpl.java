package com.example.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.QuestionsRepository;
import com.example.entities.Questions;

@Service
public class QuestionsMetierImpl implements QuestionsMetier {
	@Autowired
	  private QuestionsRepository questionsRep;
	
	@Override
	public Questions getOneQuestion(long id_Question) {
		Optional<Questions> questions = questionsRep.findById(id_Question);
		if (questions.isPresent()) { 
			return questions.get();
		}else throw new RuntimeException("question est indisponible");
	}

	@Override
	public List<Questions> getQuestions() {
		return questionsRep.findAll();
	}

	@Override
	public void deleteQuestions(long id_Question) {
		Optional<Questions> interview = questionsRep.findById(id_Question);
		if (interview.isPresent()) { 
			questionsRep.deleteById(id_Question);
	    }else throw new RuntimeException("can't delete question");
	}

	@Override
	public Questions AddQuestions(Questions questionsAdd) {
		Optional<Questions> questions = questionsRep.findById(questionsAdd.getId_Question());
		if (questions.isPresent() == false) { 
			return questionsRep.save(questionsAdd);
		}else throw new RuntimeException("you can't add a new question");
	}

	@Override
	public Questions EditQuestions(long id_Question, Questions questionsUpdate) {
		Questions questions = questionsRep.findById(id_Question).orElseThrow(()->new ResourceNotFoundException("can't find question"));
    	
		questions.setId_Question(questionsUpdate.getId_Question());
		questions.setQuestion(questionsUpdate.getQuestion());
	
    	
		questionsRep.save(questions);
			
	  	return questions;
	}

	
	
}