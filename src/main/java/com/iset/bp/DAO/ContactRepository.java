package com.iset.bp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.iset.bp.entities.Contact;

public interface ContactRepository  extends JpaRepository<Contact,Integer>{

	@Transactional
	@Modifying
	@Query("delete Contact c where c.id_Contact=:code")
	void deleteContact(@PathVariable("code") int code);
}
