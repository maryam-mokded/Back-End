package com.example.metier;

import java.util.List;

import com.example.entities.Role;

public interface InterRoleMetier {
	public Role getOneRole(long idRole);
	public List<Role> getRole();
	public void deleteRole(long idRole);
	public Role AddRole(Role role);
	public Role EditRole(long idRole,Role role);
	//public User getRoleByCode(long idUser,User user);
}
