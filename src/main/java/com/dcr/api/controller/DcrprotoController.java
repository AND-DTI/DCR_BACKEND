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

import com.dcr.api.model.as400.Dcrproto;
import com.dcr.api.model.dto.DcrprotoDTO;
import com.dcr.api.service.as400.DcrprotoService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/protocolo")
public class DcrprotoController {
	@Autowired
	DcrprotoService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos as protocolos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrproto> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
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
	@Operation(summary = "Busca um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam String protdcre) {
	
		try {
			if(protdcre.length() > 10) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Protdcre não pode ser maior que 10 caracteres!");
			}
			Optional<Dcrproto> lista = service.getByKey(protdcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
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
	@Operation(summary = "Cria um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse protocolo já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody DcrprotoDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse protocolo já existe!");
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
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody DcrprotoDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Protocolo não encontrado!");
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
	@Operation(summary = "Deleta um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam String protdcre) {
	
		try {
			
			Optional<Dcrproto> lista = service.getByKey(protdcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Protocolo deletado com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
