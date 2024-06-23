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

import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.dto.Dcrreg3DTO;
import com.dcr.api.model.keys.Dcrreg3Key;
import com.dcr.api.service.as400.Dcrreg3Service;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/registro/tres")
public class RegistroTresController {
	@Autowired
	Dcrreg3Service service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos os registros")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum registro encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrreg3> lista = service.getAll();
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
	@Operation(summary = "Cria um registro")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "registro criado!"),
	        @ApiResponse(responseCode = "400", description = "registro com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createregistro(@RequestBody Dcrreg3DTO dto, HttpServletRequest request) {
	
		try {
			Dcrreg3Key key = new Dcrreg3Key();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setNumcomp(dto.numcomp());
			key.setTpprd(dto.tpprd());
			key.setNumsubcomp(dto.numsubcomp());
			
			Optional<Dcrreg3> dcr = service.getByKey(key);
			
			if (!dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("registro já existe!");
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
	@Operation(summary = "Cria um registro")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "registro criado!"),
	        @ApiResponse(responseCode = "400", description = "registro com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody Dcrreg3DTO dto, HttpServletRequest request) {
	
		try {
			Dcrreg3Key key = new Dcrreg3Key();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setNumcomp(dto.numcomp());
			key.setTpprd(dto.tpprd());
			key.setNumsubcomp(dto.numsubcomp());
			Optional<Dcrreg3> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("registro não encontrado!");
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
	@Operation(summary = "Busca o registro ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "registro criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum registro encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestParam Integer idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
			
			List<Dcrreg3> dcr = service.getByIds(idmatriz, partnumpd, tpprd);
					
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum registro encontrado!");
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
