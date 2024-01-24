package com.dcr.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.configs.security.Security;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.dto.Login;
import com.dcr.api.model.dto.User_x_RoleDTO;
import com.dcr.api.response.LoginResponse;
import com.dcr.api.service.TokenCST;
import com.dcr.api.service.TokenService;
import com.dcr.api.service.as400.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    TokenService tokenService;
//    @Autowired
//    private CtproleRepository roleRepository;

    @Autowired
    Environment env;

    @Autowired
    Security sec;

    
    @PostMapping(value = "/login", produces = "application/json")
    @Operation(summary = "Autenticar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> autheticateSimples(@RequestBody Login login) {

        Optional<Accuser> optUser = userService.getByUsernameOptional(login.username());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Accept", "application/json")
                    .body(null);
        }

        try {
        	
	        UsernamePasswordAuthenticationToken userpassAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                login.username().toUpperCase(), login.password());
	        
	        Authentication authenticate = this.authManager.authenticate(userpassAuthenticationToken);
	
	        var usuario = (Accuser) authenticate.getPrincipal();
	        
	        TokenCST token = tokenService.gerarToken2(usuario);
	
	        usuario.setToken(token.token());
	        userService.save0(usuario);
	        
	        LoginResponse response = new LoginResponse();
	        response.setRoles(usuario.getRoles());
	        response.setToken(token.token());
	        response.setUsername(usuario.getUsername());
	        
	        return ResponseEntity.status(HttpStatus.OK) 
	                .header("Accept", "application/json")
	                .body(response);          
	        
        } catch (BadCredentialsException be) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
            		.header("Accept", "application/json")
            		.body("Usuário ou senha inválidos");             
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
            		.header("Accept", "application/json")
            		.body(ae.getMessage());                
        }        

    }
    
    @PostMapping(value = "/login2", produces = "application/json")
    @Operation(summary = "Autenticar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema FERG.COM."),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User_x_RoleDTO> autheticate2(@RequestBody Login login) {

        Optional<Accuser> optUser = userService.getByUsernameOptional(login.username());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Accept", "application/json")
                    .body(null);
        }
        Accuser user = optUser.get();

        UsernamePasswordAuthenticationToken userpassAuthenticationToken = new UsernamePasswordAuthenticationToken(
                login.username().toUpperCase(), login.password());

        try {
            try {

                Authentication authenticate = this.authManager.authenticate(userpassAuthenticationToken);

                var usuario = (Accuser) authenticate.getPrincipal();

                TokenCST token = tokenService.gerarToken2(usuario);

                usuario.setToken(token.token());

                userService.save0(usuario);

                User_x_RoleDTO user_role = new User_x_RoleDTO(user.getUsername(), user.getName(), user.getIdarea(),
                        user.getAtivo(), token.token(), null);
//                List<RoleUserDTO> rolesUser = roleRepository.findRoleUserDTOs(user.getUsername());
//                user_role.setRoles(rolesUser);

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Accept", "application/json")
                        .body(user_role);

            } catch (BadCredentialsException be) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Accept", "application/json")
                        .body(null);

            }
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Accept", "application/json")
                    .body(null);

        }

    }

    @PostMapping(value = "/roles", produces = "application/json")
    @Operation(summary = "Pegar Roles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retornadas com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema FERG.COM."),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User_x_RoleDTO> getroles(@RequestBody Login login) {

        Optional<Accuser> optUser = userService.getByUsernameOptional(login.username());
        if (optUser.isEmpty()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Accept", "application/json")
                    .body(null);

        }
        Accuser user = optUser.get();

        User_x_RoleDTO user_role = new User_x_RoleDTO(user.getUsername(), user.getName(), user.getIdarea(),
                user.getAtivo(), "", null);
//        List<RoleUserDTO> rolesUser = roleRepository.findRoleUserDTOs(user.getUsername());
//
//        if (rolesUser.isEmpty()) {
//            RoleUserDTO ur = new RoleUserDTO(user.getUsername(), 0, "USER", "Basic user access", "");
//            rolesUser.add(ur);
//        }
//
//        user_role.setRoles(rolesUser);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Accept", "application/json")
                .body(user_role);

    }

    @PostMapping(value = "/login3", produces = "application/json")
    @Operation(summary = "Autenticar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário não cadastrado no sistema FERG.COM."),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> autheticate(@RequestBody Login login) {

        Optional<Accuser> optUser = userService.getByUsernameOptional(login.username());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Accept", "application/json")
                    .body(null);

        }
        Accuser user = optUser.get();

        UsernamePasswordAuthenticationToken userpassAuthenticationToken = new UsernamePasswordAuthenticationToken(
                login.username().toUpperCase(), login.password());

        try {
            try {

                Authentication authenticate = this.authManager.authenticate(userpassAuthenticationToken);

                var usuario = (Accuser) authenticate.getPrincipal();

                TokenCST token = tokenService.gerarToken2(usuario);

                usuario.setToken(token.token());

                userService.save0(usuario);

                User_x_RoleDTO user_role = new User_x_RoleDTO(user.getUsername(), user.getName(), user.getIdarea(),
                        user.getAtivo(), token.token(), null);
//                List<RoleUserDTO> rolesUser = roleRepository.findRoleUserDTOs(user.getUsername());
//                user_role.setRoles(rolesUser);

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Accept", "application/json")
                        .body(token.token());

            } catch (BadCredentialsException be) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Accept", "applicatiDon/json")
                        .body(null);

            }
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Accept", "application/json")
                    .body(null);

        }

    }



    @PostMapping(value = "/login0", produces = "application/json")

    @Operation(summary = "Autenticar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso!"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> autheticate0(@RequestBody Login login) {

        List<Accuser> users = userService.listByUsername(login.username());
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Accept", "application/json")
                    .body("Usuário não cadastrado no sistema FERG.COM");
        }

        UsernamePasswordAuthenticationToken userpassAuthenticationToken = new UsernamePasswordAuthenticationToken(
                login.username().toUpperCase(), login.password());

        try {
            try {

                Authentication authenticate = this.authManager.authenticate(userpassAuthenticationToken);

                var usuario = (Accuser) authenticate.getPrincipal();
                String token = tokenService.gerarToken(usuario);

                usuario.setToken(token);
                userService.save(usuario);

                return ResponseEntity.status(HttpStatus.OK)
                        .header("Accept", "application/json")
                        .body(token);

            } catch (BadCredentialsException be) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .header("Accept", "application/json")
                        .body("Usuário ou senha inválidos");

            }
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Accept", "application/json")
                    .body(ae.getMessage());

        }

    }

}
