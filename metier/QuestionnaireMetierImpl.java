package com.example.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.QuestionnaireRepository;
import com.example.dao.QuestionsRepository;
import com.example.entities.Questionnaire;
import com.example.entities.Questions;

@Service
public class QuestionnaireMetierImpl implements QuestionnaireMetier {
	@Autowired
	  private QuestionnaireRepository questionnaireRep;
	
	@Override
	public Questionnaire getOneQuestionnaire(long id_Questionnaire) {
		Optional<Questionnaire> questionnaire = questionnaireRep.findById(id_Questionnaire);
		if (questionnaire.isPresent()) { 
			return questionnaire.get();
		}else throw new RuntimeException("Questionnaire est indisponible");
	}

	@Override
	public List<Questionnaire> getQuestionnaire() {
		return questionnaireRep.findAll();
	}

	@Override
	public void deleteQuestionnaire(long id_Questionnaire) {
		Optional<Questionnaire> questionnaire = questionnaireRep.findById(id_Questionnaire);
		if (questionnaire.isPresent()) { 
			questionnaireRep.deleteById(id_Questionnaire);
	    }else throw new RuntimeException("can't delete questionnaire");
	}

	@Override
	public Questionnaire AddQuestionnaire(Questionnaire questionnaireAdd) {
		Optional<Questionnaire> questionnaire = questionnaireRep.findById(questionnaireAdd.getId_Questionnaire());
		if (questionnaire.isPresent() == false) { 
			return questionnaireRep.save(questionnaireAdd);
		}else throw new RuntimeException("you can't add a new Questionnaire");
	}

	@Override
	public Questionnaire EditQuestionnaire(long id_Questionnaire, Questionnaire questionnaireUpdate) {
		Questionnaire questionnaire = questionnaireRep.findById(id_Questionnaire).orElseThrow(()->new ResourceNotFoundException("can't find questionnaire"));
    	
		questionnaire.setId_Questionnaire(questionnaireUpdate.getId_Questionnaire());
		questionnaire.setValidate(questionnaireUpdate.getValidate());
		
		questionnaireRep.save(questionnaire);
			
	  	return questionnaire;
	}

	
	
}