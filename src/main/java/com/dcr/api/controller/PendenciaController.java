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
import com.dcr.api.model.as400.Cadtppend;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.CadtppendDTO;
import com.dcr.api.model.projection.CadtppendJoinPendrespDTO;
import com.dcr.api.response.Interface.DocumentoIMP;
import com.dcr.api.response.Interface.DocumentoNAC;
import com.dcr.api.service.as400.CadtppendService;
import com.dcr.api.service.as400.PartnumberService;
import com.dcr.api.service.as400.PendastecService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.service.as400.PendrespService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/pendencia")
public class PendenciaController {


	@Autowired
	CadtppendService service;
	
	@Autowired
	PendprodService pendprod;
	
	@Autowired
	PendrespService pendresp;
	
	@Autowired
	PendastecService pendastec;

	@Autowired
	PartnumberService partnumberService;
	
	

	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as pendências")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Cadtppend> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência encontrado!");
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
	


	@GetMapping(value = "/getByID", produces = "application/json")
	@Operation(summary = "Busca pendência por ID")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma pendência encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByID(@RequestParam String id) {
	
		try {
			
			Optional<Cadtppend> lista = service.getByID(id);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência encontrado!");
	        }
	        Auxiliar.formatResponse(lista.get());
	        Auxiliar.formatResponseList2(lista.get().getResponsaveis());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body(lista);
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	

    @GetMapping(value = "/getByID2", produces = "application/json")
	@Operation(summary = "Busca pendência por ID/Subtipo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma pendência encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByID2(@RequestParam String id, String subtipo) {
	
		try {
			
			Optional<CadtppendJoinPendrespDTO> lista = service.getByID2(id, subtipo);
	        
            if(lista.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhum responsável encontrado!");                
            }
	        
            Auxiliar.formatResponse(lista.get());
            Auxiliar.formatResponseList2(lista.get().getResponsaveis());
            return ResponseEntity.status(HttpStatus.OK)
                .header("Accept", "application/json")
                .body(lista);
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}  

	}


	@DeleteMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Busca todas as pendências")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam List<String> id) {
	
		try {
			for (String string : id) {
				List<Pendprod> pendprods = pendprod.getByCdPend(string);
				List<Pendastec> pendastecs = pendastec.getByCdPend(string);
				
				if(!pendastecs.isEmpty() && !pendprods.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Não é possivel deletar essa pendência, pois ela já esta em uma matriz!");
				}
				
				Optional<Cadtppend> pend = service.getByID(string);
		        if (pend.isEmpty()) {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Nenhuma pendência encontrado!");
		        }
		        service.delete(pend.get());
			}
			
	       
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Pendência apagada");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	
	

	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Cria um Tipo de Pendência")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Pendência criada!"),
	        @ApiResponse(responseCode = "400", description = "Tipo de Pendência já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createPendencia(@RequestBody List<CadtppendDTO> dto, HttpServletRequest request) {
	
		try {
			for (CadtppendDTO cadtppendDTO : dto) {
				Optional<Cadtppend> dcr = service.getByID(cadtppendDTO.cdpend());
				
				if (!dcr.isEmpty()) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					        .header("Accept", "application/json")
					        .body("Tipo de Pendência já existe");
			    }
				
				//Cadtppend pendNew = service.create(cadtppendDTO, request);
				service.create(cadtppendDTO, request);
			}
			
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
	@Operation(summary = "Cria um Tipo de Pendência")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Pendência criada!"),
	        @ApiResponse(responseCode = "400", description = "Tipo de Pendência já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> updatePendencia(@RequestBody CadtppendDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Cadtppend> dcr = service.getByID(dto.cdpend());
			
			if (dcr.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				        .body("Tipo de Pendência não existe");
		    }
			
			//Cadtppend pendNew = service.update(dcr.get(), dto, request);
			service.update(dcr.get(), dto, request);
			
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("Ok");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@GetMapping(value = "/documentos", produces = "application/json")
	@Operation(summary = "Busca cadastro partnumber")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma partnumber encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDocumentos(@RequestParam String partnum, @RequestParam String documento, @RequestParam String tpdoc) {	
	
		try {

			//Partnumber partnumber = new Partnumber();			
			//Optional<Partnumber> lista = partnumberService.getByID(partnum);	
			if(tpdoc.equals("NF")){
				
				List<DocumentoNAC> lista = partnumberService.getDocumentoNAC(documento, partnum);
				if (lista.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.header("Accept", "application/json")
							.body("");
				}
				//Partnumber item = lista.get();
				//Auxiliar.formatResponse(lista); dont work with interface
				return ResponseEntity.status(HttpStatus.OK)
						.header("Accept", "application/json")					
						.body(lista);

			}else{

				List<DocumentoIMP> lista = partnumberService.getDocumentoIMP(documento, partnum);
				if (lista.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.header("Accept", "application/json")
							.body("");
				}
				return ResponseEntity.status(HttpStatus.OK)
						.header("Accept", "application/json")					
						.body(lista);
			}
			
	        
			

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(null);                
		}   

	}
	

} 
