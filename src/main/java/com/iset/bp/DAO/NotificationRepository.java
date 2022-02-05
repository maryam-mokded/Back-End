package com.iset.bp.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iset.bp.entities.Notification;


public interface NotificationRepository  extends JpaRepository<Notification,Integer>{
	
	/*SELECT * FROM `notification`, `user` WHERE notification.id_user = user.id_user AND user.id_user = 1*/	
	 @Query("SELECT f FROM Notification f JOIN f.user u on f.user.id_User = u.id_User AND u.id_User = ?1")
	 public List<Notification> GroupageNotification(Integer code);


}
