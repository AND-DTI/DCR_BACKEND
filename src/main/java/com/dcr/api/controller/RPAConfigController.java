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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrrpa;
import com.dcr.api.model.as400.DcrrpaDTO;
import com.dcr.api.service.as400.DcrrpaService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;





@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/config/rpa")
public class RPAConfigController {



	@Autowired
	DcrrpaService service;
	


	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as configurações de RPA")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma configuração encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrrpa> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma configuração encontrada!");
	        }
	        Auxiliar.formatResponse(lista);
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body(lista);
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   

	}
	


	@GetMapping(value = "/getByUser", produces = "application/json")
	@Operation(summary = "Busca uma configuração do usuário logado")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK!"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma configuração encontrada para o usuário!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByUser() {
	
		try {
			Optional<Dcrrpa> dcr = service.getByUser();
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhuma configuração encontrada para o usuário logado!");
		    }
			Auxiliar.formatResponse(dcr);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(dcr);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   

	}
	


	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria uma configuração para o usuário")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Configuração criada!"),
	        @ApiResponse(responseCode = "400", description = "Configuração para este usuário já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createConfig(@RequestBody DcrrpaDTO dto) {
	
		try {
			
			Optional<Dcrrpa> rpa = service.getByUser();
			if (!rpa.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Configuração já existe para o usuário!");
		    }
			
			service.create(dto);
		
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
	@Operation(summary = "Atualiza a configuração do usuário")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Configuração atualizada!"),
	        @ApiResponse(responseCode = "404", description = "Configuração para o usuário não existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> updateConfig(@RequestBody DcrrpaDTO dto) {
	
		try {
			
			Optional<Dcrrpa> rpa = service.getByUser();
			if (rpa.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Configuração para o usuário não existe!");
		    }
			
			service.update(rpa.get(), dto);
		
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
