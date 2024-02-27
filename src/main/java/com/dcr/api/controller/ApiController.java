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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.model.as400.Dcrapi;
import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.dto.DcrapiDTO;
import com.dcr.api.model.dto.DcrapiKeyDTO;
import com.dcr.api.model.dto.DcrregraKeyDTO;
import com.dcr.api.service.as400.DcrapiService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/config")
public class ApiController {

	@Autowired
	DcrapiService service;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as configurações")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma configuração encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getroles() {
	
		try {
			List<Dcrapi> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma configuração encontrada!");
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
	
	@GetMapping(value = "/getAtivo", produces = "application/json")
	@Operation(summary = "Busca uma configuração através da data")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Configuração criada!"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma configuração encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAtivo(HttpServletRequest request) {
	
		try {
			Optional<Dcrapi> dcr = service.getAtivo();
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhuma configuração encontrada!");
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
	
	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria uma configuração")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Configuração criada!"),
	        @ApiResponse(responseCode = "400", description = "Configuração com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcessamento(@RequestBody DcrapiDTO dto, HttpServletRequest request) {
	
		try {
			Optional<Dcrapi> dcr = service.getAtivo();
			DcrapiKeyDTO key = new DcrapiKeyDTO(Auxiliar.getDtFormated(), Auxiliar.getDtFormated());
			List<Dcrapi> dcrOld = service.getAll();
			
			
			if(dcrOld.size() > 1 && dcrOld.get(dcrOld.size() - 2) != null) {
				Integer num = Auxiliar.verificarCampoData(dcrOld.get(dcrOld.size() - 2).getDcrapiKey().getConfvigfim()) + 1;
				if(num > 0) {
					String dt = Auxiliar.getDtFormated() + "-" + num;
					service.update(dto, dcr.get(), request, dt);
				}else {
					String dt = Auxiliar.getDtFormated() + "-1";
					service.update(dto, dcr.get(), request, dt);
				}
			}else if (!dcr.isEmpty()) {
				service.update(dto, dcr.get(), request, Auxiliar.getDtFormated());
		    }
			
			Dcrapi dcrNew = service.create(dto, request);
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
