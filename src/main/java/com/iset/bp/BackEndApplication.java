package com.iset.bp;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iset.bp.DAO.ContactRepository;
import com.iset.bp.DAO.RoleRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Contact;
import com.iset.bp.entities.Direction;
import com.iset.bp.entities.Formation;
import com.iset.bp.entities.Role;
import com.iset.bp.entities.User;
import com.iset.bp.service.UserService;
import com.iset.bp.web.ContactController;
import com.iset.bp.web.DirectionController;
import com.iset.bp.web.FormationController;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner{
	
	@Autowired
	ContactRepository contactRep;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	UserService userSer;
	
	@Autowired
	UserRepository userRep;

	@Autowired
	ContactController contactCtr;
	
	@Autowired
	FormationController formationCtr;


	@Autowired
	DirectionController direcctionCtr;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//Ajouter Les Directions 
		Direction direction1 = new Direction(1,"Maintenance");
		direcctionCtr.AjouterDirection(direction1);
        Direction direction2 = new Direction(2,"Production");
		direcctionCtr.AjouterDirection(direction2);
		Direction direction3 = new Direction(3,"Administrative et sociale");
		direcctionCtr.AjouterDirection(direction3);
		Direction direction4 = new Direction(4,"Approvisionnement");
		direcctionCtr.AjouterDirection(direction4);
		Direction direction5 = new Direction(5,"Informatique et Contrôle de Gestion");
		direcctionCtr.AjouterDirection(direction5);
		Direction direction6 = new Direction(6,"Commerciale");
		direcctionCtr.AjouterDirection(direction6);
		Direction direction7 = new Direction(7,"Financière");
		direcctionCtr.AjouterDirection(direction7);
		Direction direction8 = new Direction(8,"Audit");
		direcctionCtr.AjouterDirection(direction8);
		Direction direction9 = new Direction(9,"Qualité / Environnement / Sécurité");
		direcctionCtr.AjouterDirection(direction9);
		
				
		//Ajouter Roles
		Role role1 = roleRep.save(new Role("USER"));
		Role role2 = roleRep.save(new Role("ADMIN"));
		Role role3 = roleRep.save(new Role("EMPLOYEE"));
		
		Set<Role> rolesAdmin = new HashSet<>();
		rolesAdmin.add(role1);			
		rolesAdmin.add(role2);
		
		Set<Role> rolesEmployee = new HashSet<>();
		rolesEmployee.add(role1);			
		rolesEmployee.add(role3);
		

		//Ajouter un Admin 
		User admin=userSer.saveUser("maryam", "123", "123");
		admin.setRoles(rolesAdmin);	admin.setNom("Admin");
		admin.setPrenom("Admin");
		admin.setAdresse("Bizerte");
		admin.setCin(10000000);
		admin.setDateEmbauche(new Date());
		admin.setEmail("admin@gmail.com");
		admin.setPhoto("photoAdmin");
		admin.setTel(52369852);
		userRep.save(admin);
		
		
		
		//Ajouter les Employees
		User employee1 =userSer.saveUser("employee1", "123", "123");
		employee1.setRoles(rolesEmployee);	
		employee1.setNom("Employee1");
		employee1.setPrenom("Employee1");
		employee1.setAdresse("Tunis");
		employee1.setCin(11111111);
		employee1.setDateEmbauche(new Date());
		employee1.setDateNaissance(new Date());
		employee1.setProfession("Profession1");
		employee1.setMatricule("1234G");
		employee1.setEmail("Employee1@gmail.com");
		employee1.setPhoto("photoEmployee1");
		employee1.setTel(58236974);
		employee1.setPilote(1);
		employee1.setDirection(direction2);
		userRep.save(employee1);
		
		User employee2 =userSer.saveUser("employee2", "123", "123");
		employee2.setRoles(rolesEmployee);	
		employee2.setNom("Employee2");
		employee2.setPrenom("Employee2");
		employee2.setAdresse("Gafsa");
		employee2.setCin(22222222);
		employee2.setDateEmbauche(new Date());
		employee2.setDateNaissance(new Date());
		employee2.setProfession("Profession2");
		employee1.setMatricule("1234F");
		employee2.setEmail("Employee2@gmail.com");
		employee2.setPhoto("photoEmployee2");
		employee2.setTel(25987456);
		employee2.setDirection(direction2);
		userRep.save(employee2);

		User employee3 =userSer.saveUser("employee3", "123", "123");
		employee3.setRoles(rolesEmployee);	
		employee3.setNom("Employee3");
		employee3.setPrenom("Employee3");
		employee3.setAdresse("Mahdia");
		employee3.setCin(33333333);
		employee3.setDateEmbauche(new Date());
		employee3.setDateNaissance(new Date());
		employee3.setProfession("Profession3");
		employee3.setMatricule("1234D");
		employee3.setEmail("Employee3@gmail.com");
		employee3.setPhoto("photoEmployee3");
		employee3.setTel(58236974);
		employee3.setDirection(direction5);
		userRep.save(employee3);
		
		//Ajouter Des Formation
		Formation formation1 = new Formation(1, "theme1", "type1","objectif1");
		formationCtr.AjouterFormation(formation1, employee1.getId_User());
		
		Formation formation2 = new Formation(2, "theme2", "type2","objectif2");
		formationCtr.AjouterFormation(formation2, employee2.getId_User());
				
		Formation formation3 = new Formation(3, "theme3", "type3","objectif3");
		formationCtr.AjouterFormation(formation3, employee3.getId_User());

		Formation formation4 = new Formation(4, "theme4", "type4","objectif4");
		formationCtr.AjouterFormation(formation4, employee2.getId_User());
	
		
				
		//Ajouter des Contact 
		Contact contact1 = new Contact(1,"Contenu de Message 1",new Date(),"Ben Khaled Amal","amalBenKhaled@gmail.com");
		contactRep.save(contact1);
		Contact contact2 = new Contact(2,"Contenu de Message 2",new Date(), "Mokded Maryam","MaryamMokded@gmail.com");
		contactRep.save(contact2);
		Contact contact3 = new Contact(3,"Contenu de Message 3",new Date(),"Ben Jemaa Hanin","HaninBEnJemaa@gmail.com");
		contactRep.save(contact3);
		Contact contact4 = new Contact(4,"Contenu de Message 4",new Date(),"Thamlaoui Achref","AchrefThamlaoui@gmail.com");
		contactRep.save(contact4);

	}

}
