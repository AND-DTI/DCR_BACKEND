package com.dcr.api.controller;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.text.ParseException;
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
import com.dcr.api.model.dto.User;
import com.dcr.api.service.AuthenticationService;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;

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
    PasswordEncoder encoder;

    @Autowired
    AuthenticationService authService;

    @Autowired
    AuthenticationManager authManager;

    @PutMapping(value = "/create", produces = "application/json")
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado!"),
            @ApiResponse(responseCode = "400", description = "Senha fora do padrão!"),
            @ApiResponse(responseCode = "500", description = "Erro interno!"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> createUser(@RequestBody User user, HttpServletRequest request) {
    	
    	Optional<Accuser> optUser = userService.getByUsernameOptional(user.username());
        if (!optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Usuário já cadastrado!");
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
			acc.setItaudusr(user.itaudusr());
			acc.setTimevrfy(new Date(0L));
	        acc.setCdvrfy("");
	        acc.setFlex1flw(new BigInteger("0"));
	        acc.setFlex2flw(Double.valueOf(0));
	        acc.setFlex3flw("");
	        acc.setFlex4flw("");
	        acc.setFlex5flw("");
	        acc.setToken("");
	        acc.setUserid(2);
	        acc.setAtivo(user.ativo());
	        userService.save(acc, request);
		} catch (UnknownHostException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Accept", "application/json")
                    .body("Erro interno!");
		}
        
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body("Usuário cadastrado com sucesso!");

    }
    
//    @GetMapping(value = "/checktoken", produces = "application/json")
//    @Operation(summary = "Simple request to check token return")
//    public ResponseEntity<String> checkToken() {
//        return ResponseEntity.status(HttpStatus.OK).body("Token alive!");
//
//    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!"),
            @ApiResponse(responseCode = "200", description = "Ok!")
    })
    public ResponseEntity<List<Accuser>> listAll(
            @PageableDefault(page = 0, size = 10, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {

        List<Accuser> users = userService.listarTodos();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
    public ResponseEntity<Object> update(@RequestBody Accuser user, HttpServletRequest request) throws ParseException {

        List<Accuser> users = userService.listByUsername(user.getUsername());
        Accuser userALT = null;

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body(null);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            try {
            	userALT = userService.save(user, request);
            } catch (UnknownHostException e) {
            		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .header("Accept", "application/json")
		                .body("Erro!");
            }
            
        }

        return ResponseEntity.status(HttpStatus.OK)
                .header("Accept", "application/json")
                .body(userALT);
    }
}