package com.dcr.api.controller;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.as400.Mtastedoc;
import com.dcr.api.model.as400.Mtasteins;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.dto.PendastecDTO;
import com.dcr.api.model.dto.PendastecDTO3;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.keys.MtastedocKey;
import com.dcr.api.model.keys.MtasteinsKey;
import com.dcr.api.model.keys.PendastecKey;
import com.dcr.api.service.as400.CadppbService;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.MtastecService;
import com.dcr.api.service.as400.MtastedocService;
import com.dcr.api.service.as400.MtasteinsService;
import com.dcr.api.service.as400.PartnumberService;
import com.dcr.api.service.as400.PendastecService;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/pendencia/astec")
public class MatrizPendenciaAstecController {

	
	@Autowired
	PendastecService pendastecService;	
	@Autowired
	MtastedocService mtastedocService;
	@Autowired	
	MtastecService mtastecService;
	@Autowired
	MtasteinsService mtasteinsService;

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
			List<Pendastec> lista = pendastecService.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
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
	public ResponseEntity<Object> getById(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam Integer numpend) {
	
		try {
			PendastecKey key = new PendastecKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			Optional<Pendastec> lista = pendastecService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
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
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse Matriz de pendencia já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody PendastecDTO dto, HttpServletRequest request) {
	
		try {


			PendastecKey key = new PendastecKey();
			key.setIdmatriz(dto.idmatriz());			
			key.setPartnum(dto.partnum());			
			//key.setNumpend(dto.numpend()); sequencial controlado pelo service

	        pendastecService.create2(dto, request);

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
	public ResponseEntity<Object> update(@RequestBody PendastecDTO dto, HttpServletRequest request) {
	
		try {
			PendastecKey key = new PendastecKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());
			
			Optional<Pendastec> lista = pendastecService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Matriz de pendencia não encontrada!");
	        }
		
	        pendastecService.update(lista.get(), dto,  request);
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
	        @ApiResponse(responseCode = "400", description = "Pendência não encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam Integer idmatriz, @RequestParam String partnum, @RequestParam Integer numpend) {
		try {
			
			PendastecKey key = new PendastecKey();
			key.setIdmatriz(idmatriz);
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			
			Optional<Pendastec> lista = pendastecService.getByID(key);
	        if (lista.isEmpty()) { 
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Pendência não encontrada!");
	        }
		
	        pendastecService.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Matriz de pendencia deletada com sucesso!");
		
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



	@PostMapping(value = "/resolverPendenciaASTEC", produces = "application/json")
	@Operation(summary = "Resolve pendência ASTEC")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Matriz de pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> resolverPendenciaASTEC(@RequestBody resolverPendenciaDTO dto, HttpServletRequest request) {
	
		try {
			

			//Pega registro pendência
			PendastecKey key = new PendastecKey();
			key.setIdmatriz(dto.idmatriz());
			key.setPartnum(dto.partnum());
			key.setNumpend(dto.numpend());			
			Optional<Pendastec> lista = pendastecService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Pendência da matriz não encontrada!");
	        }
			Pendastec pendastec = lista.get();
		
			//Pega registro matriz
			Optional<Mtastec> mat = mtastecService.getByID(dto.idmatriz());
			if (mat.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Matriz não encontrada!");
	        }
			Mtastec mtastec = mat.get();
			 

			//Pega registro Processo (DCRPROCC)
			DcrproccKey dcrproccKey = new DcrproccKey();
			dcrproccKey.setIdmatriz(Integer.valueOf(dto.idmatriz()));
			dcrproccKey.setPartnumpd(mtastec.getPartnumpd());				
			dcrproccKey.setTpprd("PC");	
			Optional<Dcrprocc> dcr = processoservice.getByKey(dcrproccKey);
			if (dcr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.header("Accept", "application/json")
						.body("Processo da matriz (DCRPROCC) não encontrado!");
			}
			Dcrprocc dcrprocc = dcr.get();

	        

			//RESOLVE PENDENCIA DE CUSTO (Atualiza preco de venda):
			if(dto.cdpend().equals("CUS")){
																				
				mtastec.setPreco(dto.preco());					
				mtastecService.save(mtastec, request);			
				pendastecService.resolverPendencia(pendastec, dto,  request);
			}

	        	

			//RESOLVE PENDENCIA DE PPB (desobriga produto):
			//??Grava exceção (ao processar matriz - o item será inelegível e vai atualizar p/ status 7 - se ainda não foi)
			if(dto.cdpend().equals("PPB")){
			
				//1. Grava PPBEXCEPT
				int resultado = cadppbservice.desobrigaPPB(mtastec.getPartnumpd(), "PC", mtastec.getDesccom(), request);

				//2. Atualiza status p/ desobrigado (7)
				if(resultado == 1){
					processoservice.setStatus(dcrprocc, 7, request); 
				}
				
				if(resultado == 1){
					pendastecService.resolverPendencia(pendastec, dto,  request);
				}else{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.header("Accept", "application/json")
						.body("Falha ao desobrigar PPB.");
				}

			}
	        
	        

			//RESOLVE PENDÊNCIA DE DOCUMENTO - NF			
			if(dto.tpreg().equals("M")){ //tp M ==> NF, DI
				
				//1. Confirma documento do insumo
				String tpdoc = dto.tpdoc().equals("")? dto.cdpend(): dto.tpdoc();
				MtastedocKey docKey = new MtastedocKey();				
				docKey.setIdmatriz(dto.idmatriz());
				docKey.setPartnum(dto.partnum());				
				docKey.setTpdoc(tpdoc);
				Optional<Mtastedoc> doc = mtastedocService.getByID(docKey);
				String tpCrud = doc.isEmpty()? "create" : "update";

				Mtastedoc mtastedoc = tpCrud.equals("update")? doc.get() 
				 		 			:new Mtastedoc( docKey,
										dto.numdoc3(), dto.emidoc3(), dto.serdoc3(), dto.cnpjfor3(), dto.ie3(), 
										dto.adicao3(), dto.itadicao3(), dto.vlrunit3(), dto.siglaund3(), dto.codinco3(), dto.modal3()								
									);
				
				if(tpCrud.equals("update")){								
					mtastedoc.setNumdoc3(Auxiliar.trimNull(dto.numdoc3()));
					mtastedoc.setEmidoc3(Auxiliar.trimNull(dto.emidoc3()));
					mtastedoc.setSerdoc3(Auxiliar.trimNull(dto.serdoc3()));
					mtastedoc.setCnpjfor3(dto.cnpjfor3());
					mtastedoc.setIe3(Auxiliar.trimNull(dto.ie3()));
					mtastedoc.setAdicao3(Auxiliar.trimNull(dto.adicao3()));
					mtastedoc.setItadicao3(dto.itadicao3());
					mtastedoc.setVlrunit3(dto.vlrunit3());
					mtastedoc.setSiglaund3(Auxiliar.trimNull(dto.siglaund3()));
					mtastedoc.setCodinco3(Auxiliar.trimNull(dto.codinco3()));
					mtastedoc.setModal3(Auxiliar.trimNull(dto.modal3()));
				}

				mtastedocService.save(mtastedoc, request); 
				
				
				//2. Confirma novo item
				MtasteinsKey mtasteinskey = new MtasteinsKey();
				mtasteinskey.setIdmatriz(dto.idmatriz());
				mtasteinskey.setPartnum(dto.partnum());				
				
				Optional<Mtasteins> ins = mtasteinsService.getByID(mtasteinskey);
				if (ins.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.header("Accept", "application/json")
							.body("Insumo não encontrado!");
				}
				Mtasteins mtasteins = ins.get();

				mtasteins.setPartnew(dto.partnew());
				mtasteins.setVlrunit(dto.vlrunit3());
				mtasteins.setPartnewdsc(dto.partnewdsc());
				mtasteinsService.salvar(mtasteins, request); 

				
				//3. Confirma resolução da pendência 
				pendastecService.resolverPendencia(pendastec, dto,  request);


				//4. Valida documento inserido	@@Obs.: ao reprocessar - get IE, CNPJ by vendor registrer (GX PDCR007)	
				ArrayList<String []> erros = new ArrayList<>();
								
				if(mtastedoc.getKey().getTpdoc().equals("NF")){
					//mtastedoc.setIe3(""); //simula doc sem IE															
					if(mtastedoc.getCnpjfor3() == 0){ 						
						erros.add(new String[]{"NF", "CNPJ inválido"}); 
					}
					if(mtastedoc.getIe3().isBlank()){ 						
						erros.add( new String[]{"NF", "Inscrição Estadual inválida"}); 
					}				
				}

				if(mtastedoc.getKey().getTpdoc().equals("DI")){
					if(mtastedoc.getItadicao3() == 0){ 						
						erros.add(new String[]{"DI", "Item da adição não informado"}); 
					}
					if(mtastedoc.getAdicao().isBlank()){ 						
						erros.add( new String[]{"DI", "Adição inválida"}); 
					}								
				}
										
				erros.forEach( (e) -> { 
					//System.out.println(e);
					PendastecDTO3 dto3 = new PendastecDTO3(mtastec.getIdmatriz(), mtastedoc.getKey().getPartnum(), e[0], e[1]);
					try {
						pendastecService.create3(dto3, request);
					} catch (JsonProcessingException | NoSuchFieldException | SecurityException
							| IllegalArgumentException | IllegalAccessException | UnknownHostException e1) {						
						e1.printStackTrace();
					}
				} );

				//String erro1[] = {"not here","not here2"};
				//String erro2[] = {"not here3","i'm here"};
				//erros.add(erro1);
				//erros.add(erro2);
			}



			//RESOLVE PENDENCIA FINAL - END (e avança p/ diagnóstico)
			if(dto.cdpend().equals("END")){
				pendastecService.resolverPendencia(pendastec, dto,  request);
				//avança para diagnóstico
				processoservice.setStatus(dcrprocc, 3, request); 				
			}



			//CRIA CONFIRMAÇÃO DE AVANÇO PARA DIAGNÓSTICO (se não há mais pendências em aberto):
	        List<Pendastec> pendencias = pendastecService.findPendenciasZero(Long.valueOf(dto.idmatriz()));	        
	        if(pendencias.isEmpty() && !dto.cdpend().equals("END") ) {								
				pendastecService.finalizaTratativa(dto.idmatriz(), mtastec.getPartnumpd(), request); //cria cdpend 'END' dto.partnumpd() vazio
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
	@Operation(summary = "Remove pendências de diagnóstico")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Pendencia não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> removePendenciaDiagnostico(@RequestParam String idmatriz, @RequestParam String partnum, @RequestParam Integer numpend, HttpServletRequest request) {
	
		try {
			

			//Pega registro pendência
			PendastecKey key = new PendastecKey();
			key.setIdmatriz(Integer.valueOf(idmatriz));
			key.setPartnum(partnum);
			key.setNumpend(numpend);
			
			Optional<Pendastec> lista = pendastecService.getByID(key);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Pendência não encontrada!");
	        }			
			Pendastec pendastec = lista.get();
		
			pendastecService.delete(pendastec);
				
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
