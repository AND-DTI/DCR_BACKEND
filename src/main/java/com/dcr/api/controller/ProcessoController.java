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

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.dto.DcrproccDTO;
import com.dcr.api.model.dto.DcrproccKeyDTO;
import com.dcr.api.model.dto.DcrproccStatusNew;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/processo/dcr")
public class ProcessoController {
	
	@Autowired
	DcrproccService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos os Processos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrprocc> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum processo encontrado!");
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
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcesso(@RequestBody DcrproccDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
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
	
	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody DcrproccDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Processo não encontrado!");
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
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestBody DcrproccKeyDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
					
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
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
	
	@PostMapping(value = "/setStatus", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> setStatus(@RequestBody DcrproccStatusNew dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
		    }
			
			service.setStatus(dcr.get(), dto.status(), request);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("Status atualizado!");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@GetMapping(value = "/getResumo", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getResumo(@RequestParam Long idmatriz,@RequestParam String partnumpd, HttpServletRequest request) {
	
		try {
		
			Optional<ResumoProjection> dcr = service.getResumo(idmatriz, partnumpd);
					
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
		    }
			Auxiliar.formatResponse(dcr.get());
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
