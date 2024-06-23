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

import com.dcr.api.model.as400.Mtastedoc;
import com.dcr.api.model.dto.MtastedocDTO;
import com.dcr.api.model.keys.MtastedocKey;
import com.dcr.api.service.as400.MtastedocService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/documento/astec")
public class MatrizDocumentosAstecController {

	@Autowired
	MtastedocService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de documento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de documento encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Mtastedoc> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma matriz encontrada!");
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
	@Operation(summary = "Busca uma matriz de documento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de documento encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam String tpdoc) {
	
		try {
			MtastedocKey key = new MtastedocKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setTpdoc(tpdoc);
			Optional<Mtastedoc> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de documento encontrada!");
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
	@Operation(summary = "Cria uma matriz de documento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de documento já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody MtastedocDTO dto, HttpServletRequest request) {
	
		try {
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
	@Operation(summary = "Altera uma Matriz de documento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de documento não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody MtastedocDTO dto, HttpServletRequest request) {
	
		try {
			MtastedocKey key = new MtastedocKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setTpdoc(dto.tpdoc());
			
			Optional<Mtastedoc> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de documento não encontrada!");
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
	@Operation(summary = "Deleta uma matriz de documento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de documento encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam String tpdoc) {
		try {
			
			MtastedocKey key = new MtastedocKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setTpdoc(tpdoc);
			
			Optional<Mtastedoc> lista = service.getByID(key);
	        if (lista.isEmpty()) { 
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de documento encontrada!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz de documento deletada com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
