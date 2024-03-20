package com.dcr.api.controller;

import java.io.Serial;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.dto.CreateUserDTO;
import com.dcr.api.model.dto.UpdateUserDTO;
import com.dcr.api.model.dto.User;
import com.dcr.api.response.ErrorResponse;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.service.AuthenticationService;
import com.dcr.api.service.as400.RoleService;
import com.dcr.api.service.as400.UserRoleService;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    UserRoleService userRoleService;
    
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationService authService;

    @Autowired
    AuthenticationManager authManager;

    @PutMapping(value = "/create", produces = "application/json")
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", useReturnTypeSchema = true),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> createUser(@RequestBody CreateUserDTO user, HttpServletRequest request) {
    	Boolean adm = Boolean.FALSE;
    	Optional<Accuser> userLogado;
		try {
			userLogado = userService.getByUsernameOptional(Auxiliar.getUser(request));
			List<RoleResponse> roles = roleService.listByUsername(userLogado.get().getRoles());
			
			for (RoleResponse roleResponse : roles) {
				if(roleResponse.getRoleName().equals("ROLE_ADMIN")) {
					adm = Boolean.TRUE;
				}
			}
			if(!adm) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Usuário não possui acessos de Administrador");
			}
		} catch (JsonProcessingException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body(e.getMessage());
		}
		
    	
    	
    	Optional<Accuser> optUser = userService.getByUsernameOptional(user.username());
        if (!optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Usuário já cadastrado!");
        }
        
        List<Accuser> listEmail = userService.getByEmail(user.email());
        if (!listEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Email já cadastrado!");
        }
        	
        if(!Auxiliar.validatePassword(user.password())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Senha fora do padrão!");
        }
        
        try {
	        Accuser acc = new Accuser();
	        acc.setName(user.name());
	        acc.setUsername(user.username());
	        acc.setEmail(user.email());
	        acc.setPassword(encoder.encode(user.password())); 
	        acc.setIdarea(user.idArea());
			acc.setTimevrfy(new Date(0L));
	        acc.setCdvrfy("");
	  
	        acc.setToken("");
	        acc.setTpfunc(user.tpfunc());
	        
	        if(user.tpfunc().equals("1")) {
	        	String id = Auxiliar.filterNumbers(user.username());
		        if(id.length() > 0) {
		        	Long idInt = Long.parseLong(id);
			        acc.setUserid(idInt);
		        }else {
		        	acc.setUserid(0L);
		        }
	        } else {
	        	acc.setUserid(0L);
	        }
	        
	        
	        acc.setAtivo(user.ativo());
	        userRoleService.createRoleUser(user.roles(), user.username(), request);
	        userService.save(acc, request);
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body(e.getMessage());
		}
        
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body("Usuário cadastrado com sucesso!");

    }
    

    @GetMapping(value = "/getAll", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!"),
            @ApiResponse(responseCode = "200", description = "Ok!")
    })
    public ResponseEntity<Object> listAll(
            @PageableDefault(page = 0, size = 10, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {

        List<Accuser> users = userService.listarTodos();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Auxiliar.formatResponse(users);
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }
    
    @GetMapping(value = "/getAtivos", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!"),
            @ApiResponse(responseCode = "200", description = "Ok!")
    })
    public ResponseEntity<Object> ativos(
            @PageableDefault(page = 0, size = 10, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {

        List<Accuser> users = userService.listarAtivos();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Auxiliar.formatResponse(users);
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }
    
    @GetMapping(value = "/getUser", produces = "application/json")
    @Operation(summary = "Listar usuário por username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!"),
            @ApiResponse(responseCode = "200", description = "Ok!")
    })
    public ResponseEntity<Accuser> listUser(@RequestParam String username) {

    	Optional<Accuser> optUser = userService.getByUsernameOptional(username);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Accept", "application/json")
                    .body(null);
        }
        Auxiliar.formatResponse(optUser.get());
        return ResponseEntity.status(HttpStatus.OK).body(optUser.get());

    }

    @PostMapping(value = "/update", produces = "application/json")
    @Operation(summary = "Alterar usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado!"),
            @ApiResponse(responseCode = "500", description = "Erro!"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> update(@RequestBody UpdateUserDTO user, HttpServletRequest request) throws ParseException {

        List<Accuser> users = userService.listByUsername(user.username());
        Accuser userALT = null;

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body(null);
        } else {

        	if(user.changePassword().toLowerCase().trim().equals("s")) {
        		users.get(0).setPassword(encoder.encode(user.password()));
        	}
            
            try {
            	users.get(0).setAtivo(user.ativo());
            	users.get(0).setEmail(user.email());
            	users.get(0).setName(user.name());
            	users.get(0).setTpfunc(user.tpfunc());
            	users.get(0).setIdarea(user.idarea());
            	userALT = userService.save(users.get(0), request);
            	userRoleService.update(user.roles(), user.username(), request);
            } catch (Exception e) {
            		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .header("Accept", "application/json")
		                .body("Erro!");
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Accept", "application/json")
                    .body(userALT);
        }
    }
}