package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRoleRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserRoleService {

	@Autowired
	UserRoleRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	public void createRoleUser(List<Integer> roles, String user, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		User_Role userRole = new User_Role();
		
		for (Integer role : roles) {
			Optional<Accroles> accrole = roleRepository.findById(role);
			
			if(accrole.isPresent()) {
				userRole.setRoleid(role);
				userRole.setRolename(accrole.get().getRolename().trim());
				userRole.setUsername(user);
				
				Auxiliar.preencheAuditoria(userRole, request);
				userRole.setDtacad(Auxiliar.getDtFormated());
				repository.save(userRole);
			}
			
		}
	}
}
