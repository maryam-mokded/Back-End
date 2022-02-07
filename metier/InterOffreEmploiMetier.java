package com.example.metier;

import java.util.List;

import com.example.entities.OffreEmploi;

public interface InterOffreEmploiMetier 

{
	public OffreEmploi getOffre(long id);
	public List<OffreEmploi> getOffres();
	public void deleteOffre(long id);
	public OffreEmploi AddOffre(OffreEmploi o);
	public OffreEmploi EditOffre(long id,OffreEmploi o);
	
}
