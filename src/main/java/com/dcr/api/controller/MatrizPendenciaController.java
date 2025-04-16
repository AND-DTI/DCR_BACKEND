package com.dcr.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.as400.Matriins;
import com.dcr.api.model.as400.Matriitm;
import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.PendprodDTO;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.keys.MatridocKey;
import com.dcr.api.model.keys.MatriinsKey;
import com.dcr.api.model.keys.MatriitmKey;
import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.service.as400.CadppbService;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.MatridocService;
import com.dcr.api.service.as400.MatriinsService;
import com.dcr.api.service.as400.MatriitmService;
import com.dcr.api.service.as400.MatriprdService;
import com.dcr.api.service.as400.PartnumberService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/pendencia")
public class MatrizPendenciaController {


	@Autowired
	PendprodService pendprodService;	
	@Autowired
	MatridocService matridocService;
	@Autowired
	MatriprdService matriprdService;
	@Autowired
	MatriitmService matriitmService;
	@Autowired
	MatriinsService matriinsService;
	
	@Autowired
	DcrproccService processoservice;	
	@Autowired
	CadppbService cadppbservice;
	@Autowired
	PartnumberService partnumberService;
	
	

	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as Matrizes de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Pendprod> lista = pendprodService.getAll();
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
	@Operation(summary = "Busca uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam String partnumpd, @RequestParam Integer numpend) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setPartnumpd(partnumpd);
			key.setNumpend(numpend);
			Optional<Pendprod> lista = pendprodService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de pendencia encontrada!");
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
	@Operation(summary = "Cria uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Pendência criada com sucesso!"),
	        @ApiResponse(responseCode = "400", description = "Matriz não existe para vinculação de pendência!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody PendprodDTO dto, HttpServletRequest request) {
	
		try {

			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnumpd(dto.partnumpd());
			key.setPartnum(dto.partnum());			
			//key.setNumpend(dto.numpend()); sequencial controlado pelo service

			/*Optional<Pendprod> pend = service.getByID(key);			
			if(pend.isPresent()) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				        	.header("Accept", "application/json")
				            .body("Matriz já existe!");
			}*/

	        pendprodService.create2(dto, request);

	        return ResponseEntity.status(HttpStatus.CREATED)
		        	.header("Accept", "application/json")
		            .body("Pendência criada com sucesso!");

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody PendprodDTO dto, HttpServletRequest request) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			key.setPartnumpd(dto.partnumpd());
			Optional<Pendprod> lista = pendprodService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Matriz de pendencia não encontrada!");
	        }
		
	        pendprodService.update(lista.get(), dto,  request);
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
	@Operation(summary = "Deleta uma matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma Matriz de pendencia encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz, @RequestParam String partnumpd, @RequestParam String partnum, @RequestParam Integer numpend) {
		try {
			
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			key.setPartnumpd(partnumpd);
			
			Optional<Pendprod> lista = pendprodService.getByID(key);
	        if (lista.isEmpty()) { 
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma Matriz de pendencia encontrada!");
	        }
		
	        pendprodService.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz de pendencia deletada com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PostMapping(value = "/resolverPendencia", produces = "application/json")
	@Operation(summary = "Resolve pendência produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> resolverPendencia(@RequestBody resolverPendenciaDTO dto, HttpServletRequest request) {
	
		try {
			

			//Pega registro pendência
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			key.setPartnumpd(dto.partnumpd());
			Optional<Pendprod> lista = pendprodService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Pendência da matriz não encontrada!");
	        }
			Pendprod pendprod = lista.get();
		
			//Pega registro matriz
			Optional<Matriprd> mat = matriprdService.getByID(dto.idmatriz());
			if (mat.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Matriz não encontrada!");
	        }
			Matriprd matriprd = mat.get();
			 
			//Pega registro item/cor
			MatriitmKey itmkey = new MatriitmKey();
			itmkey.setIdmatriz(dto.idmatriz());
			itmkey.setPartnumpd(dto.partnumpd());
			itmkey.setModelo(dto.modelo());					        
			Optional<Matriitm> itm = matriitmService.getByID(itmkey);
			if (itm.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Item/Cor não encontrada!");
			}
			Matriitm matriitm = itm.get();

			//Pega registro Processo (DCRPROCC)
			DcrproccKey dcrproccKey = new DcrproccKey();
			dcrproccKey.setIdmatriz(Integer.valueOf(dto.idmatriz()));
			dcrproccKey.setPartnumpd(dto.partnumpd());				
			dcrproccKey.setTpprd(dto.tpprd());				
			Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Processo da matriz (DCRPROCC) não encontrado!");
			}
			Dcrprocc dcrprocc = dcr.get();

	        

			//RESOLVE PENDENCIA DE CUSTO (Atualiza preco de venda):
			if(dto.cdpend().equals("CUS")){
																				
				matriitm.setPreco(dto.preco());					
				matriitmService.save(matriitm);			
				pendprodService.resolverPendencia(pendprod, dto,  request);
			}

	        	

			//RESOLVE PENDENCIA DE PPB (desobriga produto):
			//??Grava exceção (ao processar matriz - o item será inelegível e vai atualizar p/ status 7 - se ainda não foi)
			if(dto.cdpend().equals("PPB")){
			
				//1. Grava PPBEXCEPT
				int resultado = cadppbservice.desobrigaPPB(itmkey.getPartnumpd(), matriprd.getTpprd(), matriprd.getDesccom(), request);

				//2. Atualiza status p/ desobrigado (7)
				if(resultado == 1){
					processoservice.setStatus(dcrprocc, 7, request); 
				}
				
				if(resultado == 1){
					pendprodService.resolverPendencia(pendprod, dto,  request);
				}else{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Falha ao desobrigar PPB.");
				}

			}
	        
	        

			//RESOLVE PENDÊNCIA DE DOCUMENTO - NF			
			if(dto.tpreg().equals("M")){ //tp M ==> NF, DI
			
				//1. Confirma documento do insumo
				MatridocKey matridocKey = new MatridocKey();
				matridocKey.setIdmatriz(dto.idmatriz());
				matridocKey.setPartnum(dto.partnum());
				matridocKey.setPartnumpd(dto.partnumpd());
				matridocKey.setTpdoc(dto.tpdoc());
				Optional<Matridoc> doc = matridocService.getByID(matridocKey);
				String tpCrud = doc.isEmpty()? "create" : "update";

				Matridoc matridoc = tpCrud.equals("update")? doc.get() 
				 		 			:new Matridoc( matridocKey,
										dto.numdoc3(), dto.emidoc3(), dto.serdoc3(), dto.cnpjfor3(), dto.ie3(), 
										dto.adicao3(), dto.itadicao3(), dto.vlrunit3(), dto.siglaund3(), dto.codinco3(), dto.modal3()								
									);
				
				if(tpCrud.equals("update")){								
					matridoc.setNumdoc3(dto.numdoc3());
					matridoc.setEmidoc3(dto.emidoc3());
					matridoc.setSerdoc3(dto.serdoc3());
					matridoc.setCnpjfor3(dto.cnpjfor3());
					matridoc.setIe3(dto.ie3());
					matridoc.setAdicao3(dto.adicao3());
					matridoc.setItadicao3(dto.itadicao3());
					matridoc.setVlrunit3(dto.vlrunit3());
					matridoc.setSiglaund3(dto.siglaund3());
					matridoc.setCodinco3(dto.codinco3());
					matridoc.setModal3(dto.modal3());
				}

				matridocService.save(matridoc, request); //old matridocService.resolverPendencia(matridoc, request);
				
				
				//2. Confirma novo item
				MatriinsKey matriinskey = new MatriinsKey();
				matriinskey.setIdmatriz(dto.idmatriz());
				matriinskey.setPartnum(dto.partnum());
				matriinskey.setPartnumpd(dto.partnumpd());
				
				Optional<Matriins> ins = matriinsService.getByID(matriinskey);
				if (ins.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.header("Accept", "application/json")
							.body("Insumo não encontrado!");
				}
				Matriins matriins = ins.get();

				matriins.setPartnew(dto.partnew());
				matriins.setVlrunit(dto.vlrunit3());
				matriins.setPartnewdsc(dto.partnewdsc());
				matriinsService.salvar(matriins, request); //old matriinsService.resolverPendencia(matriins, request);

				
				//3. Confirma resolução da pendência
				pendprodService.resolverPendencia(pendprod, dto,  request);
												
			}


			//RESOLVE PENDENCIA FINAL - END (e avança p/ diagnóstico)
			if(dto.cdpend().equals("END")){
				pendprodService.resolverPendencia(pendprod, dto,  request);
				//avança para diagnóstico
				processoservice.setStatus(dcrprocc, 3, request); 				
			}

			//CRIA CONFIRMAÇÃO DE AVANÇO PARA DIAGNÓSTICO (se não há mais pendências em aberto):
	        List<Pendprod> pendencias = pendprodService.findPendenciasZero(Long.valueOf(dto.idmatriz()), dto.partnumpd());	        
	        if(pendencias.isEmpty() && !dto.cdpend().equals("END") ) {								
				pendprodService.finalizaTratativa(dto.idmatriz(), dto.partnumpd(), request); //cria cdpend 'END'
	        }else{
				//avança p/ "em tratativa de pendência (status 2) - ao resolver primeira pendência"
				if(dcrprocc.getStatus() == 0){
					processoservice.setStatus(dcrprocc, 2, request);
				}
			}



	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Pendência resolvida com sucesso!");


		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



	@DeleteMapping(value = "/removePendenciaDiagnostico", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> removePendenciaDiagnostico(@RequestParam String idmatriz, @RequestParam String partnumpd, @RequestParam String partnum, @RequestParam Integer numpend, HttpServletRequest request) {
	
		try {
			

			//Pega registro pendência
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(Integer.valueOf(idmatriz));
			key.setPartnumpd(partnumpd);
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			
			Optional<Pendprod> lista = pendprodService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Pendência não encontrada!");
	        }			
			Pendprod pendprod = lista.get();
		
			pendprodService.delete(pendprod);

						
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Pendência removida com sucesso!");


		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



	@GetMapping(value = "/partnumber", produces = "application/json")
	@Operation(summary = "Busca cadastro partnumber")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "404", description = "Nenhuma partnumber encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Partnumber> getPartnumber(@RequestParam String partnum) {
	//public ResponseEntity<Object> getPartnumber(@RequestParam String partnum) {
	
		try {

			//Partnumber partnumber = new Partnumber();			
			Optional<Partnumber> lista = partnumberService.getByID(partnum);			
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body(null);
	        }
			Partnumber item = lista.get();
	        Auxiliar.formatResponse(item);
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")					
		            .body(item);

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(null);                
		}   
	}
	


}


/*
@PostMapping(value = "/resolverPendencia-old", produces = "application/json")
	@Operation(summary = "Altera uma Matriz de pendencia")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> resolverPendencia_old(@RequestBody resolverPendenciaDTO dto, HttpServletRequest request) {
	
		try {
			PendprodKey key = new PendprodKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			key.setPartnumpd(dto.partnumpd());
			Optional<Pendprod> lista = service.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Matriz de pendencia não encontrada!");
	        }
		
	        
	        
	        MatridocKey matridocKey = new MatridocKey();
	        matridocKey.setIdmatriz(dto.idmatriz());
	        matridocKey.setPartnum(dto.partnum());
	        matridocKey.setPartnumpd(dto.partnumpd());
	        matridocKey.setTpdoc(dto.tpdoc());
	        Optional<Matridoc> matridoc = matridocService.getByID(matridocKey);
	        if (matridoc.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Documento não encontrado!");
	        }
	        
	        matridoc.get().setNumdoc3(dto.numdoc3());
	        matridoc.get().setEmidoc3(dto.emidoc3());
	        matridoc.get().setSerdoc3(dto.serdoc3());
	        
	        
	        
	        MatriinsKey matriinskey = new MatriinsKey();
	        matriinskey.setIdmatriz(dto.idmatriz());
	        matriinskey.setPartnum(dto.partnum());
	        matriinskey.setPartnumpd(dto.partnumpd());
	        
	        Optional<Matriins> matriins = matriinsService.getByID(matriinskey);
	        if (matriins.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Insumo não encontrado!");
	        }
	        
	        matriins.get().setPartnew(dto.partnew());
	        
	        service.resolverPendencia(lista.get(), dto,  request);
	        matriinsService.resolverPendencia(matriins.get(), request);
	        matridocService.resolverPendencia(matridoc.get(), request);
	        
	        List<Pendprod> pendencias = service.findPendenciasZero(Long.valueOf(dto.idmatriz()), dto.partnumpd());
	        
	        if(pendencias.isEmpty()) {
	        	DcrproccKey dcrproccKey = new DcrproccKey();
	        	dcrproccKey.setIdmatriz(Integer.valueOf(dto.idmatriz()));
	        	dcrproccKey.setPartnumpd(dto.partnumpd());
				
	        	dcrproccKey.setTpprd(dto.tpprd());
				
				Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
	        	processoservice.setStatus(dcr.get(), 3, request);
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

 


 */