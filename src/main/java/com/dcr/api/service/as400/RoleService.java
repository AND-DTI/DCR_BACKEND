package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.dto.Role;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRepository;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.utils.Auxiliar;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	 public List<RoleResponse> listByUsername(List<User_Role> userRoles) {
		 
		 	List<RoleResponse> roles = new ArrayList<>();
	        for (User_Role user_Role : userRoles) {
				RoleResponse response = new RoleResponse();
				Optional<Accroles> user = roleRepository.findById(user_Role.getRoleid());
				response.setRoleDesc(user.get().getRoledesc().trim());
				response.setRoleName(user.get().getRolename().trim());
				roles.add(response);
			}

	        return roles;

	 }
	 
	 public List<RoleResponse> listAllRoles() {
		 List<RoleResponse> rolesResponse =  new ArrayList<>();
		 List<Accroles> roles = roleRepository.findAll();
	        for (Accroles user_Role : roles) {
				RoleResponse response = new RoleResponse();
				Optional<Accroles> user = roleRepository.findById(user_Role.getRoleid());
				response.setRoleDesc(user.get().getRoledesc().trim());
				response.setRoleName(user.get().getRolename().trim());
				rolesResponse.add(response);
			}

	        return rolesResponse;
	 }
	 
	 public Accroles createRole(Role role, HttpServletRequest request) throws UnknownHostException {
		 Accroles newRole = new Accroles();
		 newRole.setRolename(role.roleName());
		 newRole.setRoledesc(role.roleDesc());
		 newRole.setRolecad(Auxiliar.getDtFormated());
		 newRole.setItauddt(Auxiliar.getDtFormated());
		 newRole.setItaudhr(Auxiliar.getHrFormated());
		 newRole.setItaudsys("DCR-Backend");
		 newRole.setItaudusr(role.itaudusr());
		 newRole.setItaudhst(Auxiliar.getClientHost(request));
		 return roleRepository.save(newRole);
	 }
}
