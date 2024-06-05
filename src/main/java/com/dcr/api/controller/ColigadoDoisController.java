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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrcoli2;
import com.dcr.api.model.dto.Dcrcoli2DTO;
import com.dcr.api.model.keys.Dcrcoli2Key;
import com.dcr.api.service.as400.Dcrcoli2Service;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/coligado/dois")
public class ColigadoDoisController {


	@Autowired
	Dcrcoli2Service service;
	

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
			List<Dcrcoli2> lista = service.getAll();
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
	public ResponseEntity<Object> createRegistro(@RequestBody Dcrcoli2DTO dto, HttpServletRequest request) {
	
		try {
			Dcrcoli2Key key = new Dcrcoli2Key();
			key.setDcre(dto.dcre());
			key.setNumcomp(dto.numcomp());
			
			Optional<Dcrcoli2> dcr = service.getByKey(key);
			
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
	public ResponseEntity<Object> update(@RequestBody Dcrcoli2DTO dto, HttpServletRequest request) {
	
		try {
			Dcrcoli2Key key = new Dcrcoli2Key();
			key.setDcre(dto.dcre());
			key.setNumcomp(dto.numcomp());
			
			Optional<Dcrcoli2> dcr = service.getByKey(key);
			
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
	@Operation(summary = "Busca o Registro ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Registro criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Registro encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestBody Dcrcoli2DTO dto, HttpServletRequest request) {
	
		try {
			Dcrcoli2Key key = new Dcrcoli2Key();
			key.setDcre(dto.dcre());
			key.setNumcomp(dto.numcomp());
			Optional<Dcrcoli2> dcr = service.getByKey(key);
					
			if (dcr.isEmpty()) {
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
