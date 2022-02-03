package com.iset.bp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.bp.entities.Notification;


public interface NotificationRepository  extends JpaRepository<Notification,Integer>{
}
