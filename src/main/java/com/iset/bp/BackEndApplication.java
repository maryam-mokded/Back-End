package com.iset.bp;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.iset.bp.DAO.RoleRepository;
import com.iset.bp.DAO.UserRepository;

import com.iset.bp.entities.Role;
import com.iset.bp.entities.User;
import com.iset.bp.service.UserService;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner{
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	UserService userSer;
	
	@Autowired
	UserRepository userRep;
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
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
		admin.setAdress("Bizerte");
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
		employee1.setAdress("Tunis");
		employee1.setCin(11111111);
		employee1.setDateEmbauche(new Date());
		employee1.setEmail("Employee1@gmail.com");
		employee1.setPhoto("photoEmployee1");
		employee1.setTel(58236974);
		userRep.save(employee1);
		
		User employee2 =userSer.saveUser("employee2", "123", "123");
		employee2.setRoles(rolesEmployee);	
		employee2.setNom("Employee2");
		employee2.setPrenom("Employee2");
		employee2.setAdress("Gafsa");
		employee2.setCin(22222222);
		employee2.setDateEmbauche(new Date());
		employee2.setEmail("Employee2@gmail.com");
		employee2.setPhoto("photoEmployee2");
		employee2.setTel(25987456);
	
		userRep.save(employee2);

		User employee3 =userSer.saveUser("employee3", "123", "123");
		employee3.setRoles(rolesEmployee);	
		employee3.setNom("Employee3");
		employee3.setPrenom("Employee3");
		employee3.setAdress("Mahdia");
		employee3.setCin(33333333);
		employee3.setDateEmbauche(new Date());
		employee3.setEmail("Employee3@gmail.com");
		employee3.setPhoto("photoEmployee3");
		employee3.setTel(58236974);
		userRep.save(employee3);
		
	}

}
