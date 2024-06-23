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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Accsys;
import com.dcr.api.model.dto.AccsysDTO;
import com.dcr.api.service.as400.AccsysService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/sistema")
public class SistemaController {
	@Autowired
	AccsysService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as regras")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma regra encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getroles() {
	
		try {
			List<Accsys> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum registro encontrado!");
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
	
	@GetMapping(value = "/getById", produces = "application/json")
	@Operation(summary = "Busca todas as regras")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma regra encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam String cdsys, HttpServletRequest request) {
	
		try {
			Optional<Accsys> lista = service.getByID(cdsys);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum registro encontrado!");
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
	@Operation(summary = "Cria uma regra")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Regra criada!"),
	        @ApiResponse(responseCode = "400", description = "Regra com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcessamento(@RequestBody AccsysDTO dto, HttpServletRequest request) {
	
		try {
			Optional<Accsys> dcr = service.getByID(dto.cdsys());
			
			if(dcr.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Registro já existe!");
			}
			
			Accsys dcrNew = service.create(dto, request);
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
	@Operation(summary = "Cria uma regra")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Regra criada!"),
	        @ApiResponse(responseCode = "400", description = "Regra com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody AccsysDTO dto, HttpServletRequest request) {
	
		try {
			Optional<Accsys> dcr = service.getByID(dto.cdsys());
			
			if(dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Registro não existe!");
			}
			
			Accsys dcrNew = service.update(dcr.get(), dto, request);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body("OK");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	
}
