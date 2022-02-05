package com.iset.bp.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.iset.bp.entities.Formation;

public interface FormationRepository  extends JpaRepository<Formation,Integer>{

	/*SELECT * FROM `formation`, `user` WHERE formation.id_user = user.id_userAND user.id_direction = 2*/	
	 @Query("SELECT f FROM Formation f JOIN f.user u on f.user.id_User = u.id_User AND u.direction.id_Direction = ?1")
	 public List<Formation> GroupageFormation(Integer code);


	 @Transactional
	 @Modifying
	 @Query("delete Formation f where f.id_Formation=:id")
	 void deleteFormation(@PathVariable("id") Integer id);
		
	 
}
