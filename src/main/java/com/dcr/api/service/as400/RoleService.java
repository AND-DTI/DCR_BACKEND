package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.dto.Role;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRoleRepository;
import com.dcr.api.response.ErrorResponse;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class RoleService {

	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository repository;
	


	public List<RoleResponse> listByUsername(List<User_Role> userRoles) {
		 
		 	List<RoleResponse> roles = new ArrayList<>();
	        for (User_Role user_Role : userRoles) {
				RoleResponse response = new RoleResponse();
				
			
				Optional<Accroles> user = roleRepository.findById(user_Role.getKey().getRoleid());
				response.setRoleDesc(user.get().getRoledesc().trim());
				response.setRoleName(user.get().getRolename().trim());
				response.setRoleId(user.get().getRoleid());
				roles.add(response);
			}

	        return roles;

	 }
	 
	 public Optional<Accroles> findByName(String rolename) {
		 Optional<Accroles> lista = roleRepository.consultaByRoleName(rolename);
		return lista;
	 }
	 
	 public List<RoleResponse> listAllRoles() {
		 List<RoleResponse> rolesResponse =  new ArrayList<>();
		 List<Accroles> roles = roleRepository.findAll();
	        for (Accroles user_Role : roles) {
				RoleResponse response = new RoleResponse();
				
				
				user_Role.setCdsys("NEW_DCR");
				
				Optional<Accroles> user = roleRepository.findById(user_Role.getRoleid());
				response.setRoleDesc(user.get().getRoledesc().trim());
				response.setRoleName(user.get().getRolename().trim());
				rolesResponse.add(response);
			}

	        return rolesResponse;
	 }
	 
	 public Accroles createRole(Role role, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		 Accroles newRole = new Accroles();
		
		 newRole.setCdsys("NEW_DCR"); 
		 newRole.setRolename(role.roleName());
		 newRole.setRoledesc(role.roleDesc());
		 newRole.setRolecad(Auxiliar.getDtFormated());
		 Auxiliar.preencheAuditoria(newRole, request);
		 return roleRepository.save(newRole);
	 }
	 
	 public Accroles update(Accroles accrole, Role role,  HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		 accrole.setRoledesc(role.roleDesc());
		 accrole.setRolecad(Auxiliar.getDtFormated());
		 Auxiliar.preencheAuditoria(accrole, request);
		 return roleRepository.save(accrole);
	 }
	 
	 public void delete(Accroles accrole,  HttpServletRequest request) {
		 roleRepository.delete(accrole);
	 }
	 
	 public List<User_Role> findByRolename(String rolename) {
		List<User_Role> roles = repository.findByRolename(rolename);
		return roles;
	 }
	 
	 public ErrorResponse validateRole(Role role) {
			ErrorResponse response = new ErrorResponse();
	    	response.setIsValid(Boolean.TRUE);
	    	
	    	if(role.roleName().length() > 25) {
	    		response.setIsValid(Boolean.FALSE);
	    		response.setMsg("O campo rolename é inválido!");
	    	}else if(role.roleDesc().length() > 200) {
	    		response.setIsValid(Boolean.FALSE);
	    		response.setMsg("O campo roleDesc é inválido!");
	    	}
	    
	    	
	    	return response;
	 }
}
