package com.dcr.api.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import com.dcr.api.model.as400.Cadcor;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Matriitm;
import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.MatriitmDTO;
import com.dcr.api.model.dto.MatriprdComCorDTO;
import com.dcr.api.model.dto.MatriprdComCorIdDTO;
import com.dcr.api.model.dto.MatriprdDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.keys.MatriitmKey;
import com.dcr.api.schedule.ScheduleService;
//import com.dcr.api.response.MatriprdResponse;
//import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.MatriitmService;
import com.dcr.api.service.as400.MatriprdService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/produto")
public class MatrizProdutoController {

	@Autowired
	MatriprdService service;
	
	@Autowired
	MatriitmService corService;
	
	@Autowired
	PendprodService pendservice;
	
	@Autowired
	DcrproccService processoservice;

	@Autowired
	ScheduleService scheduleService;
	

	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Matriprd> lista = service.getAll();
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
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz) {
	
		try {

			Optional<Matriprd> lista = service.getByID(idmatriz);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de produto encontrada!");
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
	@Operation(summary = "Cria um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de produto já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody MatriprdDTO dto, HttpServletRequest request) {
	
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
	


	@PutMapping(value = "/createComCor", produces = "application/json")
	@Operation(summary = "Cria um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de produto já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createComCor(@RequestBody MatriprdComCorDTO dto, HttpServletRequest request) {
	
		try {
	        Matriprd matriz = service.createComCor(dto, request);
	        
	        for (MatriitmDTO cor : dto.itens()) {
	        	
				corService.createComCor(cor, request, matriz.getIdmatriz());
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
	
	

	@PutMapping(value = "/updateComCor", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de produto não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> updateComCor(@RequestBody MatriprdComCorIdDTO dto, HttpServletRequest request) {
	
		try {
			for (MatriitmDTO cor : dto.itens()) {
				MatriitmKey key = new MatriitmKey();
				key.setIdmatriz(cor.idmatriz());
				key.setModelo(cor.modelo());
				key.setPartnumpd(cor.partnumpd());
				
				Optional<Matriitm> corOg = corService.getByID(key);
				if(!corOg.isEmpty()) {
					corService.update(corOg.get(), cor, request);
				}
				
				List<Pendprod> pendencias = pendservice.findPendenciasZero(Long.valueOf(cor.idmatriz()), cor.partnumpd());
		        

				//Se nao tem pendencia em aberto (0) - avanca para 3 (em diagnostico) senão avança para 1 (em tratativa de pendencias) //j4 adeed:
				if (cor.priocor() == 1){

					DcrproccKey dcrproccKey = new DcrproccKey();
					dcrproccKey.setIdmatriz(Long.valueOf(cor.idmatriz()));
					dcrproccKey.setPartnumpd(cor.partnumpd());					
					dcrproccKey.setTpprd(dto.tpprd());
					Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
					
					int status = pendencias.isEmpty()? 3: 1;									
					if(!dcr.isEmpty()){
						if(dcr.get().getStatus() < status){
							processoservice.setStatus(dcr.get(), status, request);
						}						
					}
					
				}


				//Gui: j4 - commented
				/*if(pendencias.isEmpty() && cor.priocor() == 1) {
		        	DcrproccKey dcrproccKey = new DcrproccKey();
		        	dcrproccKey.setIdmatriz(Long.valueOf(cor.idmatriz()));
		        	dcrproccKey.setPartnumpd(cor.partnumpd());					
		        	dcrproccKey.setTpprd(dto.tpprd());
					
					Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
		        	processoservice.setStatus(dcr.get(), 3, request);
		        }else {
		        	DcrproccKey dcrproccKey = new DcrproccKey();
		        	dcrproccKey.setIdmatriz(Long.valueOf(cor.idmatriz()));
		        	dcrproccKey.setPartnumpd(cor.partnumpd());					
		        	dcrproccKey.setTpprd(dto.tpprd());
					
					Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
		        	processoservice.setStatus(dcr.get(), 1, request);
		        }*/
				
			}
			Optional<Matriprd> lista = service.getByID(dto.idmatriz());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto não encontrada!");
	        }
	        
	        
	        service.updateComCor(lista.get(), dto,  request);
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("OK");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de produto não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody MatriprdDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Matriprd> lista = service.getByID(dto.idmatriz());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto não encontrada!");
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
	@Operation(summary = "Deleta um tipo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz) {
		try {
			
			Optional<Matriprd> lista = service.getByID(idmatriz);
			
			
			
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de produto encontrada!");
	        }
	        if(lista.get().getOrigprd().trim().toUpperCase().equals("MANUAL")) {
				 service.delete(lista.get());
			        return ResponseEntity.status(HttpStatus.OK)
				        	.header("Accept", "application/json")
				            .body("Matriz de produto deletada com sucesso!");
			} else {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Produto não pode ser excluido pois foi gerado automaticamente pelo plano de produção!");
			}
		
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}


	@GetMapping(value = "/reprocStruct", produces = "application/json")
	@Operation(summary = "Recalcula estrutura de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "400", description = "Matriz não existe"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> reprocStruct(@RequestParam Integer idmatriz, HttpServletRequest request) {
	
		try {
	       
			
			Optional<Matriprd> lista = service.getByID(idmatriz);

			if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto com este ID não encontrada!");
	        }

			Matriprd matriz = lista.get();
			if(matriz.getFlex1flw() != 0 || matriz.getFlex4flw().equals("MATRIZ PENDENTE REPROCESSAMENTO")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header("Accept", "application/json")
				.body("Matriz bloqueada por outro processo!");
			}
			
			//Chama explosão direto (HDCR004C) sem schedule (schedule somente p/ pós explosão - HDCR005C):
			matriz.setFlex4flw("MATRIZ PENDENTE REPROCESSAMENTO");
			service.save(matriz, request); //save atualiza Itaudusr	
			String tpprd = matriz.getTpprd().trim().equals("PC")? "AST" : "PRD";					
			scheduleService.explodeMatrizAvulsa(tpprd,  matriz.getItaudusr(), matriz.getIdmatriz().toString());
						
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz enviada para reprocessamento com sucesso!");


		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   

	}





}
