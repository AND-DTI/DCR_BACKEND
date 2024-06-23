package com.dcr.api.controller;

import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.Type;
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
import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.dto.DcroriprdDTO;
import com.dcr.api.model.dto.DcroriprdKeyDTO;
import com.dcr.api.model.dto.DcrregraDTO;
import com.dcr.api.model.dto.DcrregraKeyDTO;
import com.dcr.api.model.keys.DcrregraKey;
import com.dcr.api.service.as400.DcrregraService;
import com.dcr.api.utils.Auxiliar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/regras")
public class RegrasController {

	@Autowired
	DcrregraService service;
	
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
			List<Dcrregra> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma regra encontrada!");
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
	public ResponseEntity<Object> createProcessamento(@RequestBody DcrregraDTO dto, HttpServletRequest request) {
	
		try {
			Optional<Dcrregra> dcr = service.getAtivo();
			DcrregraKeyDTO key = new DcrregraKeyDTO(Auxiliar.getDtFormated(), Auxiliar.getDtFormated());
			List<Dcrregra> dcrOld = service.getAll();
			
			if(dcrOld.size() > 1 && dcrOld.get(dcrOld.size() - 2) != null) {
				Integer num = Auxiliar.verificarCampoData(dcrOld.get(dcrOld.size() - 2).getDcrregraKey().getConfvigfim()) + 1;
				if(num > 0) {
					String dt = Auxiliar.getDtFormated() + "-" + num;
					service.update(dto, dcr.get(), dt, request);
				}else {
					String dt = Auxiliar.getDtFormated() + "-1";
					service.update(dto, dcr.get(), dt, request);
				}
			}else if (!dcr.isEmpty()) {
				service.update(dto, dcr.get(), dcr.get().getDcrregraKey().getConfvigfim(), request);
		    }
			
			Dcrregra dcrNew = service.create(dto, request);
			return ResponseEntity.status(HttpStatus.CREATED)
			        .header("Accept", "application/json")
			            .body("OK");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	@GetMapping(value = "/getAtivo", produces = "application/json")
	@Operation(summary = "Busca uma regra através da data")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok!"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma regra encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAtivo(HttpServletRequest request) {
	
		try {
			Optional<Dcrregra> dcr = service.getAtivo();
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Nenhum processamento encontrado!");
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
