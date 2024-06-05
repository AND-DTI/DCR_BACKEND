package com.dcr.api.controller;
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
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.dto.CadppbDTO;
import com.dcr.api.model.projection.ListAstecProjection;
import com.dcr.api.repository.as400.PstrucRepository;
import com.dcr.api.schedule.ScheduleService;
import com.dcr.api.service.as400.CadppbService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/astec")
public class AstecController {


	@Autowired
	CadppbService service;	
	@Autowired
	PstrucRepository repoPstruc;
	@Autowired
	ScheduleService scheduleService;

	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as regras")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum produto encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {

		//scheduleService.explodeMatrizAvulsa("PRD", "92");
		
		try {

			ListAstecProjection lista = service.getAllASTEC();
	        if (lista.getProdutos().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhum produto encontrado!");
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
	


	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestParam String partnumpd) { //added j4
	
		try {
		
			Optional<Cadppb> lista = service.getByID(partnumpd);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum produto encontrado!");
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
	


	



	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Atualiza PPB ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Partnumber não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody CadppbDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Cadppb> lista = service.getByID(dto.partnumpd()); 
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Item não existe!");
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
	@Operation(summary = "Remove ppb do partnumber ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Partnumber não encontrado para remoção!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@RequestParam String partnumpd) {
	
		try {
			
			Optional<Cadppb> lista = service.getByID(partnumpd); 

	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Item não existe!");
	        }

			service.delete(lista.get());

	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("PPB ASTEC removido com sucesso!");

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
