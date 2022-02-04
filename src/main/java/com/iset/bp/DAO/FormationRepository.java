package com.iset.bp.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iset.bp.entities.Formation;
import com.iset.bp.entities.User;

public interface FormationRepository  extends JpaRepository<Formation,Integer>{

	/*SELECT * FROM `formation`, `user`
		WHERE formation.id_user = user.id_user
			AND user.id_direction = 2*/	
	
	 /*@Query("select * from User u,Formation f where f.id_user = u.id_user")
	 public List<Formation> GroupageFormation();*/
}
