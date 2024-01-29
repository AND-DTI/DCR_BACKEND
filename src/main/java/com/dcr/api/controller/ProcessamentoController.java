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

import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.dto.DcroriprdDTO;
import com.dcr.api.model.dto.DcroriprdKeyDTO;
import com.dcr.api.model.keys.DcroriprdKey;
import com.dcr.api.response.RoleResponse;
import com.dcr.api.service.as400.DcroriprdService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/processamento")
public class ProcessamentoController {

	@Autowired
	DcroriprdService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos os processamentos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum processamento encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getroles() {
	
		try {
			List<Dcroriprd> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum processamento encontrado!");
	        }
		
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
	@Operation(summary = "Cria um processamento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processamento criado!"),
	        @ApiResponse(responseCode = "400", description = "Processamento com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcessamento(@RequestBody DcroriprdDTO dto, HttpServletRequest request) {
	
		try {
			DcroriprdKeyDTO key = new DcroriprdKeyDTO(dto.confvigini(), dto.confvigfim());
			Optional<Dcroriprd> dcr = service.getByDate(key);
			
			if (!dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Processamento com essa data já existe!");
		    }
			
			Dcroriprd dcrNew = service.create(dto, request);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body(dcrNew);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Altera um processamento existente")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processamento criado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> updateProcessamento(@RequestBody DcroriprdDTO dto, HttpServletRequest request) {
	
		try {
			DcroriprdKeyDTO key = new DcroriprdKeyDTO(dto.confvigini(), dto.confvigfim());
			
			Optional<Dcroriprd> dcr = service.getByDate(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum processamento encontrado!");
		    }
			
			Dcroriprd dcrNew = service.update(dto, dcr.get(), request);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body(dcrNew);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@PostMapping(value = "/getByDate", produces = "application/json")
	@Operation(summary = "Busca um processamento através da data")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processamento criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum processamento encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByDate(@RequestBody DcroriprdKeyDTO key, HttpServletRequest request) {
	
		try {
			Optional<Dcroriprd> dcr = service.getByDate(key);
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum processamento encontrado!");
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
	
}
