package com.example.metier;

import java.util.List;

import com.example.entities.Interview;

public interface InterviewMetier {

	public List<Interview> getInterview();

	public Interview AddInterview(Interview interviewAdd);

	public Interview getOneInterview(long id_Interview);

	public void deleteInterview(long id_Interview);

	public Interview EditInterview(long id_Interview, Interview interviewUpdate);


}