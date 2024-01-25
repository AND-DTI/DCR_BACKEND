package com.dcr.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Accuser;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.service.as400.RoleService;
import com.dcr.api.service.as400.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
    @Autowired
    private UserService userService;
   
    @Autowired
    private RoleService roleService;
    
	@GetMapping(value = "/getByUser", produces = "application/json")
	@Operation(summary = "Pegar Roles.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Roles retornadas com sucesso!"),
	@ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema FERG.COM."),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getroles(@RequestParam String username) {
	
		try {
			Optional<Accuser> optUser = userService.getByUsernameOptional(username);
	        if (optUser.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	        .body("Usuário não encontrado!");
	
	        }
	
		List<RoleResponse> roles = roleService.listByUsername(optUser.get().getRoles());
		
		return ResponseEntity.status(HttpStatus.OK)
		        .header("Accept", "application/json")
		            .body(roles);
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	    
	
	}
	
	 
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Pegar Roles.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Roles retornadas com sucesso!"),
	@ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema FERG.COM."),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllroles() {
	
		try {
			List<RoleResponse> roles = roleService.listAllRoles();
			
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(roles);
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
