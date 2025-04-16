package com.dcr.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.dto.Dcrreg0DTO;
import com.dcr.api.model.keys.Dcrreg0Key;
import com.dcr.api.service.as400.Dcrreg0Service;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/registro/zero")
public class RegistroZeroController {


	@Autowired
	Dcrreg0Service service;
	


	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos os Registros")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Registro encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrreg0> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum Registro encontrado!");
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
	
	
	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria um Registro")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Registro criado!"),
	        @ApiResponse(responseCode = "400", description = "Registro com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createRegistro(@RequestBody Dcrreg0DTO dto, HttpServletRequest request) {
	
		try {
			Dcrreg0Key key = new Dcrreg0Key();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setDenom(dto.denom());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrreg0> dcr = service.getByKey(key);
			
			if (!dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Registro já existe!");
		    }
			
			service.create(dto, request);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body("OK");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Cria um Registro")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Registro criado!"),
	        @ApiResponse(responseCode = "400", description = "Registro com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody Dcrreg0DTO dto, HttpServletRequest request) {
	
		try {
			Dcrreg0Key key = new Dcrreg0Key();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setDenom(dto.denom());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrreg0> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Registro não encontrado!");
		    }
			
			service.update(dto, dcr.get(), request);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("OK");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca o Registro Zero")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Registro zero encontrado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Registro encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestParam Integer idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {

			//List<Dcrreg0> dcr = service.getById(idmatriz, partnumpd, tpprd);
			Dcrreg0 dcr = service.getById(idmatriz, partnumpd, tpprd);
					
			if (dcr == null ) {//if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum Registro encontrado!");
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


}
