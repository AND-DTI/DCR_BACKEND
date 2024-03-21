package com.dcr.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Dcrvigen;
import com.dcr.api.model.dto.DcrvigenDTO;
import com.dcr.api.service.as400.DcrvigenService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/vigencia")
public class DcrvigenController {
	@Autowired
	DcrvigenService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Vigenciaes")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Vigencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrvigen> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Vigencia encontrada!");
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
	
	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca uma Vigencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Vigencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam String dcre) {
	
		try {

			Optional<Dcrvigen> lista = service.getByKey(dcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Vigencia encontrada!");
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
	@Operation(summary = "Cria uma Vigencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Essa Vigencia já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody DcrvigenDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Dcrvigen> lista = service.getByKey(dto.dcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Essa Vigencia já existe!");
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
	@Operation(summary = "Altera uma Vigencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Vigencia não encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody DcrvigenDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrvigen> lista = service.getByKey(dto.dcre());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Vigencia não encontrada!");
	        }
		
	        service.update(lista.get(), dto,  request);
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("OK");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@DeleteMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Deleta uma Vigencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Vigencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam String dcre) {
	
		try {
			
			Optional<Dcrvigen> lista = service.getByKey(dcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Vigencia encontrada!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Vigencia deletada com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
