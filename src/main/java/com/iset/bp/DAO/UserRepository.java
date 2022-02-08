package com.iset.bp.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iset.bp.entities.User;
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(" select u from User u where u.username = ?1")
	Optional<User> findUserWithName(String username);
	
	@Query(" select u from User u where u.username = ?1")
	Optional<User> findUser(String username);
	
	//SELECT * FROM `user` WHERE user.id_direction = 2
	@Query("select u from User u where u.direction.id_Direction = ?1")
	public List<User> findByDirection(Integer code);
}

