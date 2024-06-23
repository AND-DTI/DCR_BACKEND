package com.dcr.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.response.AstecPendenciaResponse;
import com.dcr.api.response.PendenciaInsumoAstecResponse;
import com.dcr.api.response.PendenciaInsumoResponse;
import com.dcr.api.service.as400.MatriinsService;
import com.dcr.api.service.as400.MtastecService;
import com.dcr.api.service.as400.MtasteinsService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/pendencia/astec/detalhe")
public class PendenciaAstecController {

	@Autowired
	MtastecService service;
	
	@Autowired
	MatriinsService insumoService;
	
	@Autowired
	MtasteinsService insumoAStecService;
	
	@GetMapping(value = "/getPendencia", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendencia(@RequestParam Integer idmatriz) {
	
		try {

			List<AstecPendenciaResponse> lista = service.getPendencia(idmatriz);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência encontrada!");
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
	
	@GetMapping(value = "/getPendenciaInsumo", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de insumo encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendenciaInsumo(@RequestParam Integer idmatriz, @RequestParam String partnum) {
	
		try {

			List<PendenciaInsumoResponse> lista = insumoService.getPendencia(idmatriz, partnum);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de insumo encontrada!");
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
	
	@GetMapping(value = "/getPendenciaInsumoAstec", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de insumo astec encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendenciaInsumoAstec(@RequestParam Integer idmatriz, @RequestParam String partnum) {
	
		try {

			List<PendenciaInsumoAstecResponse> lista = insumoAStecService.getPendencia(idmatriz, partnum);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de insumo astec encontrada!");
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
}
