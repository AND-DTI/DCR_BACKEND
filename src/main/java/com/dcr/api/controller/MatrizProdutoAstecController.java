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
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.dto.MtastecDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.response.AstecDetailResponse;
import com.dcr.api.response.ProdutoPendenciaAstecResponse;
import com.dcr.api.response.ProdutoSemListaAstecResponse;
import com.dcr.api.schedule.ScheduleService;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.MtastecService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/astec")
public class MatrizProdutoAstecController {


	@Autowired
	MtastecService service;
	
	@Autowired
	DcrproccService processoservice;

	@Autowired
	ScheduleService scheduleService;


	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto ASTEC encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Mtastec> lista = service.getAll();
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
	


	@GetMapping(value = "/getDetail", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto ASTEC encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDetail(@RequestParam Integer idmatriz) {
	
		try {
			List<AstecDetailResponse> lista = service.getDetail(idmatriz);
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
	@Operation(summary = "Busca uma matriz de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto ASTEC encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz) {
	
		try {

			Optional<Mtastec> lista = service.getByID(idmatriz);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de produto ASTEC encontrada!");
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
	@Operation(summary = "Cria uma matriz de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de produto ASTEC já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody MtastecDTO dto, HttpServletRequest request) {
	
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
	


	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de produto ASTEC não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody MtastecDTO dto, HttpServletRequest request) {
	
		try {

			Optional<Mtastec> lista = service.getByID(dto.idmatriz());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto ASTEC não encontrada!");
	        }
			service.update(lista.get(), dto,  request);


			//Pega processo
			DcrproccKey dcrproccKey = new DcrproccKey();
			dcrproccKey.setIdmatriz(Integer.valueOf(dto.idmatriz()));
			dcrproccKey.setPartnumpd(dto.partnumpd());				
	        dcrproccKey.setTpprd("PC");
			Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);

			if (dcr.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Processo da Matriz ASTEC não encontrado!");
	        }
			Dcrprocc processo = dcr.get();

			//Set status 1 (priorizado) if 0:
			if (processo.getStatus() == 0){
				processoservice.setStatus(processo, 1, request);
			}
	        
		
	    

			/*Não avançar - após geração de pendência - GX vai add END - se não encontrar nehuma pendencia @@validar GX - implementar */
	        /*List<Pendastec> pendencias = service.findPendenciasZero(Long.valueOf(dto.idmatriz()), dto.partnumpd());	        
	        if(pendencias.isEmpty()) {	        	
	        	processoservice.setStatus(processo, 3, request);
	        }else {	           
	        	processoservice.setStatus(processo, 1, request);
	        }*/
	       
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Prioridade atualizada com sucesso!");

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}  

	}
	


	@DeleteMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Deleta uma matriz de produto ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de produto ASTEC encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz) {
		try {
			
			Optional<Mtastec> lista = service.getByID(idmatriz);
	        if (lista.isEmpty()) { 
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de produto ASTEC encontrada!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz de produto ASTEC deletada com sucesso!");
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
	       
			
			Optional<Mtastec> lista = service.getByID(idmatriz);

			if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto com este ID não encontrada!");
	        }

			Mtastec matriz = lista.get();
			if(matriz.getFlex1flw() != 0 || matriz.getFlex4flw().trim().equals("MATRIZ PENDENTE REPROCESSAMENTO")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header("Accept", "application/json")
				.body("Matriz bloqueada por outro processo!");
			}
			
			//Chama explosão direto (HDCR004C) sem schedule (schedule somente p/ pós explosão - HDCR005C):
			matriz.setFlex4flw("MATRIZ PENDENTE REPROCESSAMENTO");
			service.save(matriz, request); //save atualiza Itaudusr				
			scheduleService.explodeMatrizAvulsa("AST",  matriz.getItaudusr(), matriz.getIdmatriz().toString());
						
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz enviada para reprocessamento com sucesso!");


		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   

	}





	@GetMapping(value = "/getProdutoPendencia", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProdutoPendencia(@RequestParam Integer idmatriz/*, @RequestParam String partnum*/) {
	
		try {

			ProdutoPendenciaAstecResponse lista = service.getProdutoPendencia(idmatriz,"-", false); //old ProdutoPendenciaSimplesAstecResponse(idmatriz, partnum)
	        if (lista.getIdMatriz() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
	        }
	        Auxiliar.formatResponse(lista);
			Auxiliar.formatResponse(lista.getPendencias());
			Auxiliar.formatResponse(lista.getInsumos());
			if(!lista.getDocumentos().isEmpty()){
				Auxiliar.formatResponse(lista.getDocumentos());
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
	


	@GetMapping(value = "/getProdutoPendenciaDiagnostico", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProdutoPendenciaDiagnostico(@RequestParam Integer idmatriz) {
	
		try {

			ProdutoPendenciaAstecResponse lista = service.getProdutoPendencia(idmatriz,"-", true); //old ProdutoPendenciaSimplesAstecResponse(idmatriz, partnum)
	        if (lista.getIdMatriz() == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de diagnóstico encontrada!");
	        }
	        Auxiliar.formatResponse(lista);
			Auxiliar.formatResponse(lista.getPendencias());
			Auxiliar.formatResponse(lista.getInsumos());
			Auxiliar.formatResponseList2(lista.getDocumentos()); 
			//if(!lista.getDocumentos().isEmpty()){
			//	Auxiliar.formatResponse(lista.getDocumentos());
			//}
			
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body(lista);
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



	@GetMapping(value = "/getProdutoPendenciaBySubtype", produces = "application/json")
	@Operation(summary = "Busca pendência do produto por subtipo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProdutoPendenciaBySubtype(@RequestParam Integer idmatriz, @RequestParam String subtype, int status) {
	
		try {

			ProdutoPendenciaAstecResponse lista = service.getProdutoPendenciaBySubtype(idmatriz,subtype, status); 													
	        if (lista.getIdMatriz() == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de diagnóstico encontrada!");
	        }
	        Auxiliar.formatResponse(lista);
			Auxiliar.formatResponse(lista.getPendencias());
			Auxiliar.formatResponse(lista.getInsumos());
			Auxiliar.formatResponseList2(lista.getDocumentos()); 
						
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body(lista);
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



	@GetMapping(value = "/getPendentes", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendentes(@RequestParam List<Integer> status) {
	
		try {
			
			List<ProdutoSemListaAstecResponse> lista = service.getTodasAsPendencias(status); //j4 - old List<ProdutoPendenciaAstecResponse>

	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
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
	


	@GetMapping(value = "/getPendentesSemLista", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendentesSemLista(@RequestParam List<Integer> status) {
	
		try {

			List<ProdutoSemListaAstecResponse> lista = service.getPendenciasSemLista(status);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
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



	@GetMapping(value = "/reprocPendencies", produces = "application/json")
	@Operation(summary = "Reprocessa pendências da matriz")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK"),
	        @ApiResponse(responseCode = "400", description = "Matriz não existe"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> reprocPendencies(@RequestParam Integer idmatriz, HttpServletRequest request) {
	
		try {
	       
			
			Optional<Mtastec> lista = service.getByID(idmatriz);

			if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de produto com este ID não encontrada!");
	        }

			Mtastec matriz = lista.get();
			if(matriz.getFlex1flw() != 0 || matriz.getFlex4flw().equals("MATRIZ PENDENTE REPROCESSAMENTO")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header("Accept", "application/json")
				.body("Matriz bloqueada por outro processo!");
			}
			
			//Chama explosão direto (HDCR004C) sem schedule (schedule somente p/ pós explosão - HDCR005C):
			matriz.setFlex4flw("MATRIZ EM PROCESSANMENTO DE PENDENCIAS");
			service.save(matriz, request); //save atualiza Itaudusr	
			String tpprd = "AST"; //matriz.getTpprd().trim().equals("PC")? "AST" : "PRD";					
			scheduleService.reprocessaPendencias(tpprd, matriz.getIdmatriz().toString(), matriz.getItaudusr());
						
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz enviada para reprocessamento de pendências com sucesso!");


		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   

	}


}
