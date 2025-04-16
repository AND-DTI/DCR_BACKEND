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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg2;
import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.as400.Dcrreg9;
import com.dcr.api.model.dto.DCReDTO;
import com.dcr.api.model.dto.DcrproccDTO;
import com.dcr.api.model.dto.DcrproccKeyDTO;
import com.dcr.api.model.dto.DcrproccStatusNew;
import com.dcr.api.model.dto.JobExplosaoDTO;
import com.dcr.api.model.dto.ProcessamentoMatrizDTO;
import com.dcr.api.model.dto.RegistroLoteDTO;
import com.dcr.api.model.dto.ResumoDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.DcrprotoService;
import com.dcr.api.service.as400.Dcrreg0Service;
import com.dcr.api.service.as400.Dcrreg1Service;
import com.dcr.api.service.as400.Dcrreg2Service;
import com.dcr.api.service.as400.Dcrreg3Service;
import com.dcr.api.service.as400.Dcrreg4Service;
import com.dcr.api.service.as400.Dcrreg9Service;
import com.dcr.api.service.as400.PendastecService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/processo/dcr")
public class ProcessoController {
	


	@Autowired
	DcrproccService service;
	@Autowired
	Dcrreg0Service serviceReg0;
	@Autowired
	Dcrreg1Service serviceReg1;
	@Autowired
	Dcrreg2Service serviceReg2;
	@Autowired
	Dcrreg3Service serviceReg3;
	@Autowired
	Dcrreg4Service serviceReg4;
	@Autowired
	Dcrreg9Service serviceReg9;
	@Autowired
	PendprodService servicePend;
	@Autowired
	PendastecService serviceAstecPend;
	@Autowired
	DcrprotoService serviceProto;



	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos os Processos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Nenhum processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrprocc> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhum processo encontrado!");
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
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "400", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createProcesso(@RequestBody DcrproccDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (!dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        .header("Accept", "application/json")
				            .body("Processo já existe!");
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
	@Operation(summary = "Cria um Processo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "404", description = "Processo com essa data já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> update(@RequestBody DcrproccDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
				        .header("Accept", "application/json")
				            .body("Processo não encontrado!");
		    }
			
			service.update(dto, dcr.get(), request);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("OK");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}
	


	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByKey(@RequestBody DcrproccKeyDTO dto, HttpServletRequest request) {
	
		try {
			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setTpprd(dto.tpprd());
			
			Optional<Dcrprocc> dcr = service.getByKey(key);
					
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
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
	


	@PostMapping(value = "/setStatus", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> setStatus(@RequestBody DcrproccStatusNew dto, HttpServletRequest request) {
	
		try {

			DcrproccKey key = new DcrproccKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());			
			key.setTpprd(dto.tpprd());			
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
		    }

			Integer old_status = dcr.get().getStatus();			
			service.setStatus(dcr.get(), dto.status(), request);

			//From 3,4 (diagnostic/preview) to 2 (pendency) - remove CDPEND='END', and move diagnostic protocol to history		
			if((old_status == 3 || old_status == 4) && dto.status() == 2){
				if(dto.tpprd().equals("PC")){
					serviceAstecPend.removeEND(dto.idmatriz(), request);                    
				}else{
					servicePend.removeEND(dto.idmatriz(), dto.partnumpd(), request);
				}		
                //move diagnostic protocol to history
                if(old_status == 3)					{
                    serviceProto.geraHistorico(dto.idmatriz(), dto.partnumpd(), dto.tpprd());
                }				
			}


			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("Status atualizado!");
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}
	


	@GetMapping(value = "/getResumo", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getResumo(@RequestParam Long idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
		
			//Optional<ResumoProjection> dcr = service.getResumo(idmatriz, partnumpd);
			ResumoDTO dcr = service.getResumo(idmatriz, partnumpd, tpprd);
					
			//if(dcr.isEmpty()) {
			if(dcr == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
		    }
						
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(dcr); //old dcr.get()
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



	@GetMapping(value = "/geraRegistros", produces = "application/json") //"text/plain" causes error in axios requisition
	@Operation(summary = "Gera registros da matriz (0 a 9)")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Registros criados!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado para geração de registros!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> geraRegistros(@RequestParam Integer idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
		

			DcrproccKey key = new DcrproccKey(idmatriz, partnumpd, tpprd);					
			Optional<Dcrprocc> dcr = service.getByKey(key);
			
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado para geração de registros!");
		    }

			String resultado = "";

			if(tpprd.equals("PC")){
				resultado = service.geraRegistrosMatrizAstec(idmatriz, partnumpd, request);
			}else{
				resultado = service.geraRegistrosMatriz(idmatriz, partnumpd, request);
			}
			
											
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(resultado);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



	@GetMapping(value = "/getRegistros", produces = "application/json") 
	@Operation(summary = "Pega registros da matriz (0 a 9)")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Registros listados!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado para recuperação de registros!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getRegistros(@RequestParam Integer idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
		

			
			Dcrreg0 reg0 = serviceReg0.getById(idmatriz, partnumpd, tpprd);
			List<Dcrreg1> reg1 = serviceReg1.getByIds(idmatriz, partnumpd, tpprd);
			List<Dcrreg2> reg2 = serviceReg2.getByIds(idmatriz, partnumpd, tpprd);
			List<Dcrreg3> reg3 = serviceReg3.getByIds(idmatriz, partnumpd, tpprd);
			List<Dcrreg4> reg4 = serviceReg4.getByIds(idmatriz, partnumpd, tpprd);
			Dcrreg9 reg9 = serviceReg9.getByIds(idmatriz, partnumpd, tpprd);

			//if (reg0.isEmpty() || reg1.isEmpty() || reg2.isEmpty() || reg3.isEmpty() || reg4.isEmpty() || reg9.isEmpty()) {
			if (reg0 == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Registro Zero não encontrado para a matriz!");
		    }
			
			RegistroLoteDTO lote = new RegistroLoteDTO();
			lote.setIdmatriz(idmatriz);
			lote.setPartnumpd(partnumpd);
			lote.setTpprd(tpprd);
			lote.setReg0(reg0);
			lote.setReg1(reg1);
			lote.setReg2(reg2);
			lote.setReg3(reg3);
			lote.setReg4(reg4);
			lote.setReg9(reg9);
			Auxiliar.formatResponse(lote);

																	
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(lote);
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}


    
	@GetMapping(value = "/getDCRe", produces = "application/json")
	@Operation(summary = "Busca o Processo ativo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Processo criado!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum Processo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDCRe(@RequestParam Long idmatriz, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
		
			DCReDTO dcr = service.getDCRe(idmatriz, partnumpd, tpprd);
					
			if(dcr == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum Processo encontrado!");
		    }
						
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(dcr); 
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



    @GetMapping(value = "/getProcessando", produces = "application/json")
	@Operation(summary = "Busca matriz em processamento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK!"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma matriz em processamento encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProcessando() {
	
		try {
		
            List<ProcessamentoMatrizDTO> procs = service.getProcessando();
			
					
			if(procs.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhuma matriz em processamento encontrada!");
		    }
					
            Auxiliar.formatResponseList2(procs);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(procs); 
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



    @GetMapping(value = "/getJobExplosao", produces = "application/json")
	@Operation(summary = "Busca matriz em processamento")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK!"),
	        @ApiResponse(responseCode = "404", description = "Nenhum job de explosão pendende!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getJobExplosao() {
	
		try {
		
            List<JobExplosaoDTO> procs = service.getJobExplosao();
			
					
			if(procs.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Nenhum job de explosão pendende!");
		    }
					
            Auxiliar.formatResponseList2(procs);
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body(procs); 
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



    @GetMapping(value = "/liberaJobExplosao", produces = "application/json")
	@Operation(summary = "Libera job Explosão")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK!"),	        
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> liberaJobExplosao(@RequestParam String userid, @RequestParam Integer nrojob) {
	
		try {
		
            service.liberaJobExplosao(userid, nrojob);
										
			return ResponseEntity.status(HttpStatus.OK)
			        .header("Accept", "application/json")
			            .body("OK"); 
	       
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    		.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	
	}



    @GetMapping(value = "/liberaMatriz", produces = "application/json")
	@Operation(summary = "Libera status bloqueio Matriz")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "OK!"),	        
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> liberaMatriz(@RequestParam Long idmatriz, @RequestParam String tpprd) {
	
		try {
		
            service.liberaMatriz(idmatriz, tpprd);
										
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
