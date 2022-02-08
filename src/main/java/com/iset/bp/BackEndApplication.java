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
		admin.setNom("Admin");
		admin.setPrenom("Admin");
		admin.setAdresse("Bizerte");
		admin.setCin(10000000);
		admin.setDateEmbauche(new Date());
		admin.setEmail("admin@gmail.com");
		admin.setPhoto("photoAdmin");
		admin.setUsername("admin");	
		encoder = new BCryptPasswordEncoder();
		admin.setPassword(encoder.encode("admin"));
		admin.setTel(52369852);
		userRep.save(admin);
		


		//Ajouter un ChefServiceFormation
		User ChefFormation = new Chef_Service_Formation();
		ChefFormation.setRoles(rolesChefServiceFormation);	
		ChefFormation.setNom("Mokded");
		ChefFormation.setPrenom("Maryam");
		ChefFormation.setAdresse("Bizerte");
		ChefFormation.setCin(10000000);
		ChefFormation.setChef_Service(1);
		ChefFormation.setDateEmbauche(new Date());
		ChefFormation.setEmail("MokdedMaryam@gmail.com");
		ChefFormation.setPhoto("photoMaryam");
		ChefFormation.setUsername("Maryam-Mokded");	
		encoder = new BCryptPasswordEncoder();
		ChefFormation.setPassword(encoder.encode("maryam"));
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
		employee1.setProfession("Profession1");
		employee1.setMatricule("1234G");
		employee1.setEmail("GuerfaliNour@gmail.com");
		employee1.setPhoto("photoEmployee1");
		employee1.setTel(58236974);
		employee1.setPilote(1);
		employee1.setUsername("nour");	
		encoder = new BCryptPasswordEncoder();
		employee1.setPassword(encoder.encode("nour"));
		employee1.setDirection(direction1);
		userRep.save(employee1);
		
		User employee2 = new Employee();
		employee2.setRoles(rolesEmployee);	
		employee2.setNom("Mejri");
		employee2.setPrenom("Sarrah");
		employee2.setMatricule("12584M");
		employee2.setAdresse("Gafsa");
		employee2.setCin(22222222);
		employee2.setDateEmbauche(new Date());
		employee2.setDateNaissance(new Date());
		employee2.setProfession("Profession2");
		employee2.setEmail("MejriSarrh@gmail.com");
		employee2.setPhoto("photoEmployee2");
		employee2.setTel(25987456);
		employee2.setDirection(direction1);
		userRep.save(employee2);

		User employee3 =  new Employee();
		employee3.setRoles(rolesEmployee);	
		employee3.setNom("Amri");
		employee3.setPrenom("Mohamed");
		employee3.setAdresse("Mahdia");
		employee3.setCin(33333333);
		employee3.setDateEmbauche(new Date());
		employee3.setDateNaissance(new Date());
		employee3.setProfession("Profession3");
		employee3.setMatricule("1234D");
		employee3.setEmail("MohamedAmri@gmail.com");
		employee3.setPhoto("photoEmployee3");
		employee3.setTel(58236974);
		employee3.setDirection(direction1);
		userRep.save(employee3);

		
		//Ajouter les Employees du direction 2 
		User employee4 =  new Pilote();
		employee4.setRoles(rolesPilote);	
		employee4.setNom("Ben Jemaa");
		employee4.setPrenom("Hanin");
		employee4.setAdresse("Tunis");
		employee4.setCin(44444444);
		employee4.setDateEmbauche(new Date());
		employee4.setDateNaissance(new Date());
		employee4.setProfession("Profession4");
		employee4.setMatricule("1234G");
		employee4.setEmail("BenJemaaHanin@gmail.com");
		employee4.setPhoto("photoEmployee4");
		employee4.setTel(58236974);
		employee4.setUsername("hanin");	
		encoder = new BCryptPasswordEncoder();
		employee4.setPassword(encoder.encode("hanin"));
		employee4.setPilote(1);
		employee4.setDirection(direction2);
		userRep.save(employee4);
		
		User employee5 = new Employee();
		employee5.setRoles(rolesEmployee);	
		employee5.setNom("Moneem");
		employee5.setPrenom("Salim");
		employee5.setAdresse("Gafsa");
		employee5.setCin(22222222);
		employee5.setDateEmbauche(new Date());
		employee5.setDateNaissance(new Date());
		employee5.setProfession("Profession2");
		employee5.setMatricule("1234F");
		employee5.setEmail("MoneemSalim@gmail.com");
		employee5.setPhoto("photoEmployee2");
		employee5.setTel(25987456);
		employee5.setDirection(direction2);
		userRep.save(employee5);

		User employee6 = new Employee();
		employee6.setRoles(rolesEmployee);	
		employee6.setNom("Ben Hajria");
		employee6.setPrenom("Riham");
		employee6.setAdresse("Mahdia");
		employee6.setCin(33333333);
		employee6.setDateEmbauche(new Date());
		employee6.setDateNaissance(new Date());
		employee6.setProfession("Profession3");
		employee6.setMatricule("1234D");
		employee6.setEmail("BenHajriaRihem@gmail.com");
		employee6.setPhoto("photoEmployee3");
		employee6.setTel(58236974);
		employee6.setDirection(direction2);
		userRep.save(employee6);
	
		
		
		//Ajouter Des Formation
		Formation formation1 = new Formation(1, "theme1", "type1","objectif1");
		formationCtr.AjouterFormation(formation1, employee1.getId_User());
		
		Formation formation2 = new Formation(2, "theme2", "type2","objectif2");
		formationCtr.AjouterFormation(formation2, employee2.getId_User());
				
		Formation formation3 = new Formation(3, "theme3", "type3","objectif3");
		formationCtr.AjouterFormation(formation3, employee3.getId_User());

		Formation formation4 = new Formation(4, "theme4", "type4","objectif4");
		formationCtr.AjouterFormation(formation4, employee2.getId_User());

		Formation formation5 = new Formation(5, "theme5", "type5","objectif5");
		formationCtr.AjouterFormation(formation5, employee5.getId_User());
	
		Formation formation6 = new Formation(6, "theme6", "type6","objectif6");
		formationCtr.AjouterFormation(formation6, employee6.getId_User());
	
		Formation formation7 = new Formation(7, "theme7", "type7","objectif7");
		formationCtr.AjouterFormation(formation7, employee5.getId_User());
	
		Formation formation8 = new Formation(8, "theme8", "type8","objectif8");
		formationCtr.AjouterFormation(formation8, employee6.getId_User());
	
		
				
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
