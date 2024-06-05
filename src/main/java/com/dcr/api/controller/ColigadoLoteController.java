package com.dcr.api.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrcoli0;
import com.dcr.api.model.dto.ColigadoLoteDTO;
import com.dcr.api.model.keys.Dcrcoli0Key;
import com.dcr.api.service.as400.Dcrcoli0Service;
import com.dcr.api.service.as400.Dcrcoli1Service;
import com.dcr.api.service.as400.Dcrcoli2Service;
import com.dcr.api.service.as400.Dcrcoli3Service;
import com.dcr.api.service.as400.Dcrcoli4Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/coligado/")
public class ColigadoLoteController {


	@Autowired
	Dcrcoli0Service coligadoZeroService;	
	@Autowired
	Dcrcoli1Service coligadoUmService;	
	@Autowired
	Dcrcoli2Service coligadoDoisService;	
	@Autowired
	Dcrcoli3Service coligadoTresService;	
	@Autowired
	Dcrcoli4Service coligadoQuatroService;
	
	
	//@PutMapping(value = "/createEmLote", produces = "application/json")
	@PutMapping(value = "/enviaMovimentoDCR-e", produces = "application/json")
	@Operation(summary = "Cria coligados em Lote.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Lote criado!"),
	        @ApiResponse(responseCode = "400", description = "Erro no formato do lote!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createRegistro(@RequestBody ColigadoLoteDTO dto, HttpServletRequest request) {
	
		try {
			
			Dcrcoli0Key key = new Dcrcoli0Key();
			key.setDenom(dto.getDenom());
			key.setDcre(dto.getDcre());
			
			Optional<Dcrcoli0> dcr = coligadoZeroService.getByKey(key);
			
			if (!dcr.isEmpty()) {
				coligadoZeroService.delete(dcr.get(), request);
		    }
			
			coligadoZeroService.createLote(dto, request);
			
			coligadoUmService.createLote(dto.getColiUm(), dto.getDcre(), dto.getCdclient(), request);
			
			coligadoDoisService.createLote(dto.getColiDois(), dto.getDcre(), request);
			
			coligadoTresService.createLote(dto.getColiTres(), dto.getDcre(), request);
			
			coligadoQuatroService.createLote(dto.getColiQuatro(), dto.getDcre(), request);
			
			return ResponseEntity.status(HttpStatus.CREATED) 
					.header("Accept", "application/json")
						.body("Lote criado!");           
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
}
