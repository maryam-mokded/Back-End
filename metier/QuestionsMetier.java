package com.example.metier;

import java.util.List;

import com.example.entities.Questions;

public interface QuestionsMetier {

	public List<Questions> getQuestions();

	public Questions AddQuestions(Questions questionsAdd);

	public void deleteQuestions(long id_Question);
	
	public Questions getOneQuestion(long id_Question);

	public Questions EditQuestions(long id_Question, Questions questionsUpdate);

	

}