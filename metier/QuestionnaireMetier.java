package com.example.metier;

import java.util.List;

import com.example.entities.Questionnaire;
import com.example.entities.Questions;


public interface QuestionnaireMetier {


	public List<Questionnaire> getQuestionnaire();

	public Questionnaire getOneQuestionnaire(long id_Questionnaire);

	public void deleteQuestionnaire(long id_Questionnaire);

	public Questionnaire AddQuestionnaire(Questionnaire questionnaireAdd);

	public Questionnaire EditQuestionnaire(long id_Questionnaire, Questionnaire questionnaireUpdate);



}