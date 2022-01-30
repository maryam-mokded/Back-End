package com.iset.bp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.bp.entities.Contact;

public interface ContactRepository  extends JpaRepository<Contact,Integer>{

}
