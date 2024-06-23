package com.dcr.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.as400.Matriins;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.PendprodDTO;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.keys.MatridocKey;
import com.dcr.api.model.keys.MatriinsKey;
import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.MatridocService;
import com.dcr.api.service.as400.MatriinsService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/pendencia")
public class MatrizPendenciaController {
	@Autowired
	PendprodService service;
	
	@Autowired
	MatridocService matridocService;
	
	@Autowired
	MatriinsService matriinsService;
	
	@Autowired
	DcrproccService processoservice;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Pendprod> lista = service.getAll();
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
	@Operation(summary = "Busca uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam String partnumpd, @RequestParam Integer numpend) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setPartnumpd(partnumpd);
			key.setNumpend(numpend);
			Optional<Pendprod> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de pendencia encontrada!");
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
	@Operation(summary = "Cria uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de pendencia já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody PendprodDTO dto, HttpServletRequest request) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setPartnumpd(dto.partnumpd());
			key.setNumpend(dto.numpend());
			Optional<Pendprod> pend = service.getByID(key);
			
			if(pend.isPresent()) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        	.header("Accept", "application/json")
				            .body("Matriz já existe!");
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
	@Operation(summary = "Altera uma Matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody PendprodDTO dto, HttpServletRequest request) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			key.setPartnumpd(dto.partnumpd());
			Optional<Pendprod> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de pendencia não encontrada!");
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
	@Operation(summary = "Deleta uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam String partnumpd, @RequestParam Integer numpend) {
		try {
			
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			key.setPartnumpd(partnumpd);
			
			Optional<Pendprod> lista = service.getByID(key);
	        if (lista.isEmpty()) { 
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de pendencia encontrada!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz de pendencia deletada com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	

	@PostMapping(value = "/resolverPendencia", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> resolverPendencia(@RequestBody resolverPendenciaDTO dto, HttpServletRequest request) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			key.setPartnumpd(dto.partnumpd());
			Optional<Pendprod> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de pendencia não encontrada!");
	        }
		
	        
	        
	        MatridocKey matridocKey = new MatridocKey();
	        matridocKey.setIdmatriz(dto.idmatriz());
	        matridocKey.setPartnum(dto.partnum());
	        matridocKey.setPartnumpd(dto.partnumpd());
	        matridocKey.setTpdoc(dto.tpdoc());
	        Optional<Matridoc> matridoc = matridocService.getByID(matridocKey);
	        if (matridoc.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Documento não encontrado!");
	        }
	        
	        matridoc.get().setNumdoc3(dto.numdoc3());
	        matridoc.get().setEmidoc3(dto.emidoc3());
	        matridoc.get().setSerdoc3(dto.serdoc3());
	        
	        
	        
	        MatriinsKey matriinskey = new MatriinsKey();
	        matriinskey.setIdmatriz(dto.idmatriz());
	        matriinskey.setPartnum(dto.partnum());
	        matriinskey.setPartnumpd(dto.partnumpd());
	        
	        Optional<Matriins> matriins = matriinsService.getByID(matriinskey);
	        if (matriins.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Insumo não encontrado!");
	        }
	        
	        matriins.get().setPartnew(dto.partnew());
	        
	        service.resolverPendencia(lista.get(), dto,  request);
	        matriinsService.resolverPendencia(matriins.get(), request);
	        matridocService.resolverPendencia(matridoc.get(), request);
	        
	        List<Pendprod> pendencias = service.findPendenciasZero(Long.valueOf(dto.idmatriz()), dto.partnumpd());
	        
	        if(pendencias.isEmpty()) {
	        	DcrproccKey dcrproccKey = new DcrproccKey();
	        	dcrproccKey.setIdmatriz(Long.valueOf(dto.idmatriz()));
	        	dcrproccKey.setPartnumpd(dto.partnumpd());
				
	        	dcrproccKey.setTpprd(dto.tpprd());
				
				Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
	        	processoservice.setStatus(dcr.get(), 3, request);
	        }
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
