package com.example.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.RoleRepository;
import com.example.dao.UserRepository;
import com.example.entities.Role;
import com.example.entities.User;

@Service
public class ImplRole  implements InterRoleMetier {
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role getOneRole(long idRole) {
		Optional<Role> role = roleRepository.findById(idRole);
		if (role.isPresent()) { 
			return role.get();
		}else throw new RuntimeException("Role introuvable !! ");
	}


	@Override
	public List<Role> getRole() {
		return roleRepository.findAll();
		}

	@Override
	public void deleteRole(long idRole) {
		Optional<Role> role = roleRepository.findById(idRole);
		if (role.isPresent()) { 
			roleRepository.deleteById(idRole);
	    }else throw new RuntimeException("Role introuvable on ne peut pas le supprimer !");
	}

	@Override
	public Role AddRole(Role role) {
		Optional<Role> r =  roleRepository.findById(role.getIdRole());
		if (r.isPresent() == false) { 
			return roleRepository.save(role);
		}else throw new RuntimeException("role est déjà existe");
	}

	@Override
	public Role EditRole(long idRole, Role role) {
		Role r = roleRepository.findById(idRole).orElseThrow(()->new ResourceNotFoundException("Ct utilisateur n'existe pas"));
		r.setIdRole(role.getIdRole());
		r.setName(role.getName());
		roleRepository.save(r);
	  	return r;
	}

}
