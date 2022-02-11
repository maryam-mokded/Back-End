package com.iset.bp;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.iset.bp.DAO.ContactRepository;
import com.iset.bp.DAO.FormationRepository;
import com.iset.bp.DAO.RoleRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Admin;
import com.iset.bp.entities.Chef_Service_Formation;
import com.iset.bp.entities.Contact;
import com.iset.bp.entities.Direction;
import com.iset.bp.entities.Employee;
import com.iset.bp.entities.Formation;
import com.iset.bp.entities.Pilote;
import com.iset.bp.entities.Role;
import com.iset.bp.entities.User;
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
	UserRepository userRep;

	@Autowired
	ContactController contactCtr;
	
	@Autowired
	FormationController formationCtr;

	@Autowired
	FormationRepository formationRep;
	
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
		Role role4 = roleRep.save(new Role("CHEF_SERVICE"));
		Role role5 = roleRep.save(new Role("PILOTE"));
		
		Set<Role> rolesAdmin = new HashSet<>();
		rolesAdmin.add(role1);			
		rolesAdmin.add(role2);
		
		Set<Role> rolesEmployee = new HashSet<>();
		rolesEmployee.add(role1);			
		rolesEmployee.add(role3);

		Set<Role> rolesChefServiceFormation = new HashSet<>();
		rolesChefServiceFormation.add(role1);			
		rolesChefServiceFormation.add(role3);			
		rolesChefServiceFormation.add(role4);
		
		Set<Role> rolesPilote = new HashSet<>();
		rolesPilote.add(role1);			
		rolesPilote.add(role3);			
		rolesPilote.add(role5);
		
		
		BCryptPasswordEncoder encoder; 
		encoder = new BCryptPasswordEncoder();


		//Ajouter un Admin 
		User admin = new Admin();
		admin.setRoles(rolesAdmin);	
		admin.getRoles().add(role1);
		admin.getRoles().add(role2);		
		admin.setNom("Mokded");
		admin.setPrenom("Marayam");
		admin.setAdresse("Bizerte");
		admin.setCin(10000000);
		admin.setDateEmbauche(new Date());
		admin.setEmail("MaryamMokded@gmail.com");
		admin.setMatricule("1236M");
		admin.setProfession("Admin");
		admin.setUsername("Maryam-Mokded");	
		encoder = new BCryptPasswordEncoder();
		admin.setPassword(encoder.encode("maryam"));
		admin.setTel(52369852);
		userRep.save(admin);
		


		//Ajouter un ChefServiceFormation
		User ChefFormation = new Chef_Service_Formation();
		ChefFormation.setRoles(rolesChefServiceFormation);	
		ChefFormation.setNom("Thamlaoui");
		ChefFormation.setPrenom("Achref");
		ChefFormation.setAdresse("Bizerte");
		ChefFormation.setCin(10000000);
		ChefFormation.setChef_Service(1);
		ChefFormation.setDateEmbauche(new Date());
		ChefFormation.setEmail("AchrefThamlaoui@gmail.com");
		ChefFormation.setMatricule("1598K");
		ChefFormation.setProfession("Chef Service Formation");
		ChefFormation.setUsername("Achref-Thamlaoui");	
		encoder = new BCryptPasswordEncoder();
		ChefFormation.setPassword(encoder.encode("achref"));
		ChefFormation.setTel(52369852);
		userRep.save(ChefFormation);

		
		//Ajouter les Employees du direction 1 
		User employee1 = new Pilote();
		employee1.setRoles(rolesPilote);	
		employee1.setNom("Guerfali");
		employee1.setPrenom("Nour");
		employee1.setAdresse("Tunis");
		employee1.setCin(11111111);
		employee1.setDateEmbauche(new Date());
		employee1.setDateNaissance(new Date());
		employee1.setProfession("Pilote Direction Maintenance");
		employee1.setMatricule("1234G");
		employee1.setEmail("GuerfaliNour@gmail.com");
		employee1.setPhoto("photoEmployee1");
		employee1.setTel(58236974);
		employee1.setPilote(1);
		employee1.setUsername("Nour-Guerfali");	
		encoder = new BCryptPasswordEncoder();
		employee1.setPassword(encoder.encode("nour"));
		employee1.setDirection(direction1);
		userRep.save(employee1);
				
		//Ajouter les Employees du direction 2 
		User employee4 =  new Pilote();
		employee4.setRoles(rolesPilote);	
		employee4.setNom("Ben Jemaa");
		employee4.setPrenom("Hanin");
		employee4.setAdresse("Tunis");
		employee4.setCin(44444444);
		employee4.setDateEmbauche(new Date());
		employee4.setDateNaissance(new Date());
		employee4.setProfession("Pilote Direction Production");
		employee4.setMatricule("1234G");
		employee4.setEmail("BenJemaaHanin@gmail.com");
		employee4.setTel(58236974);
		employee4.setUsername("Hanin-Ben-Jemaa");	
		encoder = new BCryptPasswordEncoder();
		employee4.setPassword(encoder.encode("hanin"));
		employee4.setPilote(1);
		employee4.setDirection(direction2);
		userRep.save(employee4);
		
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
