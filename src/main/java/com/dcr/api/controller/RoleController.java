package com.dcr.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Accroles;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.dto.Role;
import com.dcr.api.response.ErrorResponse;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.service.as400.RoleService;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
public class RoleController {
	

    @Autowired
    private UserService userService;
   
    @Autowired
    private RoleService roleService;
    


	@GetMapping(value = "/getByUser", produces = "application/json")
	@Operation(summary = "Busca role por usuário.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Usuário não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
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
		Auxiliar.formatResponse(roles);
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
	@Operation(summary = "Busca todas as roles.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	@ApiResponse(responseCode = "500", description = "Error!"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllroles() {
	
		try {
			List<RoleResponse> roles = roleService.listAllRoles();
			Auxiliar.formatResponse(roles);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(roles);
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/createRole", produces = "application/json")
	@Operation(summary = "Criação de role")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	@ApiResponse(responseCode = "500", description = "Error"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> createRole(@RequestBody Role role, HttpServletRequest request) {
	
		try {
			Optional<Accroles> roles = roleService.findByName(role.roleName());
			
			if(!roles.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Role já existe!");
			}
			ErrorResponse response = roleService.validateRole(role);
			if(!response.getIsValid()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body(response.getMsg());
			}
			
			//Accroles roleNew =  roleService.createRole(role, request);
			roleService.createRole(role, request);
			
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body("OK");
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PostMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Criação de role")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	@ApiResponse(responseCode = "500", description = "Error"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody Role role, HttpServletRequest request) {
	
		try {
			Optional<Accroles> roles = roleService.findByName(role.roleName());
			
			if(roles.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Role não existe!");
			}
			
			List<User_Role> lista = roleService.findByRolename(role.roleName());
			if(!lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Essa role já tem amarrações!");
			}
			
			ErrorResponse response = roleService.validateRole(role);
			if(!response.getIsValid()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body(response.getMsg());
			}
			
			roleService.update(roles.get(), role, request);
			
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("OK");
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PostMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Criação de role")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	@ApiResponse(responseCode = "500", description = "Error"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> Delete(@RequestBody Role role, HttpServletRequest request) {
	
		try {
			Optional<Accroles> roles = roleService.findByName(role.roleName());
			
			if(roles.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Role não existe!");
			}
			
			List<User_Role> lista = roleService.findByRolename(role.roleName());
			if(!lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Essa role já tem amarrações!");
			}
			
			ErrorResponse response = roleService.validateRole(role);
			if(!response.getIsValid()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body(response.getMsg());
			}
			
			roleService.delete(roles.get(), request);
			
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("OK");
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}


}
