package com.dcr.api.controller;
import java.util.ArrayList;
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
import com.dcr.api.model.as400.Pendresp;
import com.dcr.api.model.dto.PendrespDTO;
import com.dcr.api.model.dto.PendrespDTO2;
import com.dcr.api.model.dto.PendrespDeleteDTO;
import com.dcr.api.model.keys.PendenciaKey;
import com.dcr.api.response.PendrespDeleteResponse;
import com.dcr.api.response.PendrespResponse;
import com.dcr.api.service.as400.PendrespService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/pendencia/responsavel")
public class ResponsavelPendenciaController {


	@Autowired
	PendrespService service;

	
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Pendresp> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum responsável encontrado!");
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
	


	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestParam String cdpend, @RequestParam String cdresp) {
	
		try {
			PendenciaKey key = new PendenciaKey();
			key.setCdpend(cdpend);
			key.setCdresp(cdresp);
			
			Optional<Pendresp> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum responsável encontrado!");
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
	


	@GetMapping(value = "/getResponsaveis", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByPendencia(@RequestParam String cdpend) {
	
		try {
	
			List<Pendresp> lista = service.getByCdPend(cdpend);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum responsável encontrado!");
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
	


	@PostMapping(value = "/delete", produces = "application/json") //j4 - DeleteMapping error - body missing
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestBody List<PendrespDeleteDTO> listaDto, HttpServletRequest request) {
	//public ResponseEntity<Object> delete(@RequestParam List<PendrespDeleteDTO> listaDto, HttpServletRequest request) {
		List<PendrespDeleteDTO> listaErro = new ArrayList<>();
		try {
			for (PendrespDeleteDTO dto : listaDto) {
				PendenciaKey key = new PendenciaKey();
				key.setCdpend(dto.cdpend());
				key.setCdresp(dto.cdresp());
				
				Optional<Pendresp> lista = service.getByID(key);
		        if (lista.isEmpty()) {
		            listaErro.add(dto);
		        }else {
		        	service.delete(lista.get());
		        }
			
		        
		       
			}
			if(listaErro.size() > 0) {
				PendrespDeleteResponse resp = new PendrespDeleteResponse();
				resp.setErros(listaErro);
				resp.setMsgErro("Associações não encontradas!");
				return ResponseEntity.status(HttpStatus.OK)
			        	.header("Accept", "application/json")
			            .body(resp);
			}
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Associação deletada com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	//public ResponseEntity<Object> create(@RequestBody List<PendrespDTO> listaDto, HttpServletRequest request) {
    public ResponseEntity<Object> create(@RequestBody PendrespDTO2 listaDto, HttpServletRequest request) {
		
        List<PendrespDTO> listaErro = new ArrayList<>();

		try {
			//for (PendrespDTO dto : listaDto) {
            for (PendrespDTO dto : listaDto.responsaveis()) {
				
                
                if( listaDto.subtipos().size() > 0){
                    for (String subtp : listaDto.subtipos()) {

                        PendenciaKey key = new PendenciaKey();
                        key.setCdpend(dto.cdpend());
                        key.setSubtipo(subtp);
                        key.setCdresp(dto.cdresp());
                        
                        Optional<Pendresp> lista = service.getByID(key);
                        if (!lista.isEmpty()) {
                            listaErro.add(dto);
                        }else {                            
                            service.create2(dto, subtp, request);
                        }
                                               
                    }
        
                }else{
                    PendenciaKey key = new PendenciaKey();
                    key.setCdpend(dto.cdpend());
                    key.setSubtipo("");
                    key.setCdresp(dto.cdresp());
                    
                    Optional<Pendresp> lista = service.getByID(key);
                    if (!lista.isEmpty()) {
                        listaErro.add(dto);
                    }else {
                        //service.create(dto, request);
                        service.create2(dto, "", request);
                    }
                }

			}

			if(listaErro.size() > 0) {
				PendrespResponse resp = new PendrespResponse();
				resp.setErros(listaErro);
				resp.setMsgErro("Esses responsáveis já existem!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body(resp); 
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
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody List<PendrespDTO> listaDto, HttpServletRequest request) {
		List<PendrespDTO> listaErro = new ArrayList<>();
		try {
			for (PendrespDTO dto : listaDto) {
				PendenciaKey key = new PendenciaKey();
				key.setCdpend(dto.cdpend());
				key.setCdresp(dto.cdresp());
				
				Optional<Pendresp> lista = service.getByID(key);
		        if (lista.isEmpty()) {
		        	listaErro.add(dto);
		        }else if (dto.nmresp().equals(lista.get().getNmresp().trim())) {
		        	listaErro.add(dto);
		            
		        }else {
		        	service.update(lista.get(), dto,  request);
		        }
		        
			}
		    if(listaErro.size() > 0) {
	        	PendrespResponse resp = new PendrespResponse();
				resp.setErros(listaErro);
				resp.setMsgErro("Associações não encontradas ou sem alteração!");
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body(resp);
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
