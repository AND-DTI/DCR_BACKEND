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

import com.dcr.api.model.as400.Prodmod;
import com.dcr.api.model.dto.ProdmodDTO;
import com.dcr.api.service.as400.ProdmodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/prodmod")
public class ProdmodController {

	@Autowired
	ProdmodService service;
	
	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcesso(@RequestBody ProdmodDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Prodmod> dcr = service.getByID(dto.partnumpd());
			
			if (!dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Processo já existe!");
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
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Prodmod> dcr = service.getAll();
			
			if(dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
				        .header("Accept", "application/json")
				            .body("Nenhum dado encontrado!");
			}
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(dcr);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody ProdmodDTO dto, HttpServletRequest request) {
	
		try {

			Optional<Prodmod> dcr = service.getByID(dto.partnumpd());
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Processo não encontrado!");
		    }
			
			service.update(dcr.get(), dto, request);
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
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> delete(@RequestParam String partnumpd, HttpServletRequest request) {
	
		try {
			
			Optional<Prodmod> dcr = service.getByID(partnumpd);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Processo não encontrado!");
		    }
			
			service.delete(dcr.get());
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
