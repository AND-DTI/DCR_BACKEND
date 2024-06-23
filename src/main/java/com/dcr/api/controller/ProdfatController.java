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

import com.dcr.api.model.as400.Prodfat;
import com.dcr.api.model.dto.ProdfatDTO;
import com.dcr.api.model.keys.ProdfatKey;
import com.dcr.api.service.as400.ProdfatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/prodfat")
public class ProdfatController {
	
	@Autowired
	ProdfatService service;
	
	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcesso(@RequestBody ProdfatDTO dto, HttpServletRequest request) {
	
		try {
			ProdfatKey key = new ProdfatKey();
			key.setCdprd(dto.cdprd());
			key.setPartnumpd(dto.partnumpd());
			
			Optional<Prodfat> dcr = service.getByID(key);
			
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
			List<Prodfat> dcr = service.getAll();
			
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
	public ResponseEntity<Object> update(@RequestBody ProdfatDTO dto, HttpServletRequest request) {
	
		try {
			ProdfatKey key = new ProdfatKey();
			key.setCdprd(dto.cdprd());
			key.setPartnumpd(dto.partnumpd());
			
			Optional<Prodfat> dcr = service.getByID(key);
			
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
	public ResponseEntity<Object> delete(@RequestParam String cdprd, @RequestParam String partnumpd, HttpServletRequest request) {
	
		try {
			ProdfatKey key = new ProdfatKey();
			key.setCdprd(cdprd);
			key.setPartnumpd(partnumpd);
			
			Optional<Prodfat> dcr = service.getByID(key);
			
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
