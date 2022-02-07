package com.example.metier;

import java.util.List;

import com.example.entities.Condidats;
import com.example.entities.User;

public interface InterCondidatMetier {
	public Condidats getOneCondidat(long iduser);
	public List<Condidats> getCondidat();
	public void deleteCondidat(long idUser);
	public Condidats EditCondidat(long id,Condidats condidat );
	public void AddCondidat(Condidats user, long idOffre);
}
