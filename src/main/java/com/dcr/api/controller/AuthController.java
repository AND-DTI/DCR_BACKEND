package com.dcr.api.controller;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.dto.GenerateCode;
import com.dcr.api.model.dto.Login;
import com.dcr.api.model.dto.NewPassword;
import com.dcr.api.model.dto.ResetPassword;
import com.dcr.api.response.LoginResponse;
import com.dcr.api.service.TokenCST;
import com.dcr.api.service.TokenService;
import com.dcr.api.service.as400.RoleService;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;
import com.dcr.api.utils.EmailUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;
   
    @Autowired
    private UserService userService;
   
    @Autowired
    private RoleService roleService;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    TokenService tokenService;
    


    @PostMapping(value = "/login", produces = "application/json")
    @Operation(summary = "Realiza o login do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos!"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos!"),

    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> authenticateSimples(@RequestBody Login login) {

        Optional<Accuser> optUser = userService.getByUsernameOptional(login.username());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inválidos!");
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
	        response.setRoles(roleService.listByUsername(usuario.getRoles()));
	        response.setToken(token.token());
	        response.setUsername(usuario.getUsername().trim());
	        response.setIdArea(usuario.getIdarea().trim());
	        response.setName(Auxiliar.formatName(usuario.getName()));
	        
	        Auxiliar.formatResponse(response);
	        return ResponseEntity.status(HttpStatus.OK) 
	                .header("Accept", "application/json")
	                .body(response);          
	        
        } catch (BadCredentialsException be) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
            		.header("Accept", "application/json")
            		.body("Usuário ou senha inválidos!");             
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED) 
            		.header("Accept", "application/json")
            		.body(ae.getMessage());                
        }        

    }
    


    @PostMapping(value = "/generateCode", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "E-mail enviado com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Erro!"),
            @ApiResponse(responseCode = "400", description = "Dados inconsistentes!"),
    })
    public ResponseEntity<Object> generateCode(@RequestBody GenerateCode generateCode, HttpServletRequest request) {

    	Optional<Accuser> optUser = userService.getByUsernameOptional(generateCode.username());
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inconsistentes!");
        }else if(!optUser.get().getEmail().trim().equals(generateCode.email())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inconsistentes!");
        }
        
        String code = Auxiliar.getCaptcha();
        optUser.get().setCdvrfy(code);
        optUser.get().setTimevrfy(Calendar.getInstance().getTime());
        
        
        try {
        	EmailUtil.sendMail(generateCode.email(), code);
		} catch (Exception e) {
			String msg = "Método main() da classe EmailUtil lançou uma Exception: "
					+  "=> " + e.getMessage();
	
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Accept", "application/json")
                    .body(msg);	
		}
        
        try {
        	userService.save(optUser.get(), request);
        	return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Accept", "application/json")
                    .body("E-mail enviado com sucesso!");
	    } catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .header("Accept", "application/json")
		                .body("Erro!");
		}
        
    }
    


    @PostMapping(value = "/resetPassword", produces = "application/json")
    @Operation(summary = "Reset de senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Dados inconsistentes!"),
            @ApiResponse(responseCode = "400", description = "Código inválido!"),
            @ApiResponse(responseCode = "400", description = "Código expirado!"),
            @ApiResponse(responseCode = "400", description = "Senha fora do padrão!"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Erro!"),
    })
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPassword reset, HttpServletRequest request) {

    	Optional<Accuser> optUser = userService.getByUsernameOptional(reset.username());
    	
    	 Long dataOld = optUser.get().getTimevrfy().getTime();
         Long dataNew = Calendar.getInstance().getTime().getTime();
         
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inconsistentes!");
        }else if(!optUser.get().getCdvrfy().trim().equals(reset.code())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Código inválido!");
        }else if ((dataNew - dataOld) > 120000) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Código expirado!");
		}else if(!Auxiliar.validatePassword(reset.password())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Senha fora do padrão!");
        }
        
        try {
	          optUser.get().setPassword(encoder.encode(reset.password()));
	          
	          userService.save(optUser.get(), request);
	          
	          return ResponseEntity.status(HttpStatus.OK)
	                  .header("Accept", "application/json")
	                  .body("Senha alterada com sucesso!");
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .header("Accept", "application/json")
		                .body("Erro!");
		}
    }



    @PostMapping(value = "/newPassword", produces = "application/json")
    @Operation(summary = "Nova senha")
    @ApiResponses(value = {
    		 @ApiResponse(responseCode = "400", description = "Dados inconsistentes!"),
             @ApiResponse(responseCode = "400", description = "Senha fora do padrão!"),
             @ApiResponse(responseCode = "200", description = "Ok"),
             @ApiResponse(responseCode = "500", description = "Erro!")
    })
    public ResponseEntity<Object> resetPassword(@RequestBody NewPassword pass, HttpServletRequest request) {

    	Optional<Accuser> optUser = userService.getByUsernameOptional(pass.username());
    	
    	
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inconsistentes!");
        }else if(!encoder.matches(pass.passwordOld(), optUser.get().getPassword())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Dados inconsistentes!");
        }else if(!Auxiliar.validatePassword(pass.passwordNew())) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .header("Accept", "application/json")
                    .body("Senha fora do padrão!");
        }
      
        try {
	          optUser.get().setPassword(encoder.encode(pass.passwordNew()));
	         
	          userService.save(optUser.get(), request);
	          
	          return ResponseEntity.status(HttpStatus.OK)
	                  .header("Accept", "application/json")
	                  .body("Senha alterada com sucesso!");
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .header("Accept", "application/json")
		                .body("Erro!");
		}
       
    }

}