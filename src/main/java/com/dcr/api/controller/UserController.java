package com.dcr.api.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.dcr.api.configs.security.Security;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.dto.User;
import com.dcr.api.service.AuthenticationService;
import com.dcr.api.service.as400.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Autowired
    Security sec;

    @Value("${data.source:hda}")
    String ENV;
    @Value("${data.api_se.adm:https://...}")
    String api_se_adm;
    @Value("${data.api_se.base_url:https://...}")
    String api_se_baseurl;
    @Value("${app.name:apiName}")
    String app_name;

    @PutMapping(value = "/create", produces = "application/json")
    @Operation(summary = "Autenticar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Usuário já cadastrado no sistema"),
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> autheticateSimples(@RequestBody User user) {
    	
    	Optional<Accuser> optUser = userService.getByUsernameOptional(user.username());
        if (!optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Usuário já cadastrado!");
        }
        
        Accuser acc = new Accuser();
        acc.setName(user.name());
        acc.setUsername(user.username());
        acc.setEmail(user.email());
        acc.setPassword(encoder.encode(user.password()));
        acc.setIdarea(user.idArea());
        acc.setItauddt(user.itauddt());
        acc.setItaudhr(user.itaudhr());
        acc.setItaudhst(user.itaudhst());
        acc.setItaudsys(user.itaudsys());
        acc.setItaudusr(user.itaudusr());
        acc.setToken("");
        acc.setUserid(2);
        acc.setAtivo("S");
        userService.save(acc);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body("Usuário cadastrado com sucesso!");

    }
    
    @GetMapping(value = "/checktoken", produces = "application/json")
    @Operation(summary = "Simple request to check token return")
    public ResponseEntity<String> checkToken() {

        return ResponseEntity.status(HttpStatus.OK).body("Token alive!");

    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!")
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
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!")
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
            @ApiResponse(responseCode = "201", description = "Usuário alterado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Usuário não cadastrado no sistema FERG.COM!"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Accuser> update(@RequestBody Accuser user) throws ParseException {

        List<Accuser> users = userService.listByUsername(user.getUsername());
        Accuser userALT = null;

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("Accept", "application/json")
                    .body(null);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userALT = userService.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body(userALT);

    }


}