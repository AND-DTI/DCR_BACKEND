package com.dcr.api.service.as400;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRepository;
import com.dcr.api.response.RoleResponse;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	 public List<RoleResponse> listByUsername(List<User_Role> userRoles) {
		 
		 	List<RoleResponse> roles = new ArrayList<>();
	        for (User_Role user_Role : userRoles) {
				RoleResponse response = new RoleResponse();
				Optional<Accroles> user = roleRepository.findById(user_Role.getRoleid().toString());
				response.setRoleDesc(user.get().getRoledesc());
				response.setRoleName(user.get().getRolename());
				roles.add(response);
			}

	        return roles;

	    }
}
