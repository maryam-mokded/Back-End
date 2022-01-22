package com.iset.bp.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iset.bp.entities.User;
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(" select u from User u where u.username = ?1")
	Optional<User> findUserWithName(String username);
}
