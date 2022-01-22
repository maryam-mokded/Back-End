package com.iset.bp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iset.bp.DAO.RoleRepository;
import com.iset.bp.DAO.UserRepository;
import com.iset.bp.entities.Role;
import com.iset.bp.entities.User;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserService implements UserDetailsService {

	
	private final UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRep;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;	

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	//verification
	//aceder lel base eli fyha table user a partir de cette methode (retourner un utilisateur si exist si non un exception declancher) 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		User user = userRepository.findUserWithName(username)
		.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		user.getRoles().forEach(r->{
		 authorities.add(new SimpleGrantedAuthority(r.getName()));
		});
		return new
		org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
		}
	//Ajouter un nv utilisateur dans BD avec un rolpar défaut "user"
	public User saveUser(String username, String password, String confirmedPassword) {
		 User appUser = new User();
		 if (userRepository.findUserWithName(username).isPresent() == true)
			 throw new RuntimeException("User already exists");
		 if (!password.equals(confirmedPassword))
			 throw new RuntimeException("Please confirm your password");
		 appUser.setUsername(username);
		 Set<Role> roles = new HashSet<Role>();
		 Role r = new Role("USER");
		 roleRep.save(r);
		 roles.add(r);
		 appUser.setRoles(roles);
		 appUser.setPassword(bCryptPasswordEncoder.encode(password));
		 userRepository.save(appUser);
		
		 	return appUser;
		 }
	
}

