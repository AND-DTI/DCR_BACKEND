package com.dcr.api.service.as400;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.keys.User_RoleKey;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRoleRepository;
import com.dcr.api.utils.Auxiliar;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserRoleService {

	@Autowired
	UserRoleRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	public void createRoleUser(List<String> roles, String user, HttpServletRequest request) throws Exception {
		 for (String role : roles) {
		        Optional<Accroles> accrole = roleRepository.consultaByRoleName(role);

		        try {
		            User_Role userRole = new User_Role();
		            User_RoleKey key = new User_RoleKey();
		            key.setRoleid(accrole.get().getRoleid());
		            key.setUsername(user);
		            userRole.setKey(key);
		            userRole.setRolename(accrole.get().getRolename().trim());

		            Auxiliar.preencheAuditoria(userRole, request);
		            userRole.setDtacad(Auxiliar.getDtFormated());
		            repository.save(userRole);
		        } catch (Exception e) {
		            throw new Exception();
		        }
		    }
	}
	
	public void update(List<String> roles, String user, HttpServletRequest request) throws Exception {
		List<User_Role> rolesUser = repository.findByUsername(user);
		
		for (User_Role user_Role : rolesUser) {
			if(!roles.contains(user_Role.getRolename().toString())) {
				repository.delete(user_Role);
			}
			
		}
		for (String role : roles) {
		        Optional<Accroles> accrole = roleRepository.consultaByRoleName(role);
		        
		        try {
		            User_Role userRole = new User_Role();
		            User_RoleKey key = new User_RoleKey();
		            key.setRoleid(accrole.get().getRoleid());
		            key.setUsername(user);
		            userRole.setKey(key);
		            userRole.setRolename(accrole.get().getRolename().trim());

		            Auxiliar.preencheAuditoria(userRole, request);
		            userRole.setDtacad(Auxiliar.getDtFormated());
		            
		            repository.save(userRole);
		        } catch (Exception e) {
		            throw new Exception();
		        }
		    }
	}
}
