package com.example.metier;

import java.util.List;

import com.example.entities.Cv;

public interface InterCVMetier {

	public Cv getOneCv(long id);
	public List<Cv> getCv();
	public void deleteCv(long id);
	public Cv AddCv(Cv cv);
	public Cv EditCv(long id,Cv cv);
	
}
