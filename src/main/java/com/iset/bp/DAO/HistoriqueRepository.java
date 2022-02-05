package com.iset.bp.DAO;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iset.bp.entities.Historique;

public interface HistoriqueRepository  extends JpaRepository<Historique,Integer>{

	/*SELECT * FROM `formation`, `user` WHERE formation.id_user = user.id_userAND user.id_direction = 2*/	
	 @Query("SELECT f FROM Historique f JOIN f.user u on f.user.id_User = u.id_User AND u.direction.id_Direction = ?1")
	 public List<Historique> GroupageHistorique(Integer code);

	 
}
