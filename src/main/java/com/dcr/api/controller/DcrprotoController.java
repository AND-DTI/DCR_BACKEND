package com.dcr.api.controller;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dcr.api.model.as400.Dcrproto;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.as400.Dcrvigen;
import com.dcr.api.model.dto.DcrprotoDTO;
import com.dcr.api.model.dto.GeraDiagnosticoDTO;
import com.dcr.api.model.dto.GeraRegistroDTO;
import com.dcr.api.model.dto.RegistrarDCReDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.service.as400.DcrproccService;
import com.dcr.api.service.as400.DcrprotoService;
import com.dcr.api.service.as400.Dcrreg0Service;
import com.dcr.api.service.as400.DcrregraService;
import com.dcr.api.service.as400.DcrvigenService;
import com.dcr.api.service.as400.PendastecService;
import com.dcr.api.service.as400.PendprodService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/protocolo")
public class DcrprotoController {


	@Autowired
	DcrprotoService service;
	
	@Autowired
	DcrproccService dcrProccservice;
	
	@Autowired
	DcrvigenService vigenService;
	
	@Autowired
	Dcrreg0Service reg0Service;
	
	@Autowired
	DcrregraService regraService;

	@Autowired
	PendprodService pendService;

	@Autowired
	PendastecService pendAstecService;

    @Autowired
	DcrvigenService dcrvigenService;
	


	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todos as protocolos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Dcrproto> lista = service.getAll();
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
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
	@Operation(summary = "Busca um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getById(@RequestParam String protdcre) {
	
		try {
			if(protdcre.length() > 10) {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Protdcre não pode ser maior que 10 caracteres!");
			}
			Optional<Dcrproto> lista = service.getByKey(protdcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
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
	


	@GetMapping(value = "/getByProduto", produces = "application/json")
	@Operation(summary = "Busca um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByProduto(@RequestParam Long idmatriz, @RequestParam String tpprd, @RequestParam String partnumpd) {
	
		try {
			
			Optional<Dcrproto> lista = service.getByProduto(idmatriz, tpprd, partnumpd);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
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
	@Operation(summary = "Cria um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Esse protocolo já existe!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody DcrprotoDTO dto, HttpServletRequest request) {
	
		try {
			
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse protocolo já existe!");
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
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody DcrprotoDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Protocolo não encontrado!");
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
	


	@PutMapping(value = "/geraRegistro", produces = "application/json")
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> geraRegistro(@RequestBody GeraRegistroDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse protocolo já existe!");
	        }
		
	        service.create(dto, request);
	        
	        DcrproccKey key = new DcrproccKey();
	        key.setIdmatriz(dto.idmatriz());
	        key.setPartnumpd(dto.partnumpd());
	        key.setTpprd(dto.tpprd());
	        
	        Optional<Dcrprocc> procc = dcrProccservice.getByKey(key);
	        
	        if(procc.isPresent()) {
	        	Integer status = procc.get().getStatus();
	        	
	        	procc.get().setTaxausd(dto.taxausd());
	        	procc.get().setTotalnac(dto.totalnac());
	        	procc.get().setTotalimp(dto.totalimp());
	        	procc.get().setCustotal(dto.custotal());
	        	procc.get().setCoefred(dto.coefred());
	        	procc.get().setIitotal(dto.iitotal());
	        	procc.get().setIireduzido(dto.iireduzido());
	        	procc.get().setStatus(6);
	        	
	        	dcrProccservice.update(procc.get(), request);
	        	
	        	try {
	        		//List<Dcrreg0> listareg = reg0Service.getById(dto.idmatriz().intValue(), dto.partnumpd(), dto.tpprd());
					Dcrreg0 reg0 = reg0Service.getById(dto.idmatriz().intValue(), dto.partnumpd(), dto.tpprd());
	        		Optional<Dcrregra> regra = regraService.getAtivo();
	        		
	        		String tpdcre = reg0.getTpdcre();//listareg.get(0).getTpdcre();
		        	Dcrvigen vigen = new Dcrvigen();
		        	vigen.setDcre(dto.dcre());
		        	vigen.setCoefred(dto.coefred());
		        	vigen.setCustotal(dto.custotal());
		        	vigen.setDcrant(dto.dcrant());
		        	vigen.setIdmatriz(dto.idmatriz().intValue());
		        	vigen.setIireduzido(dto.iireduzido());
		        	vigen.setIitotal(dto.iitotal());
		        	vigen.setPartnumpd(dto.partnumpd());
		        	vigen.setTaxausd(dto.taxausd());
		        	vigen.setTotalimp(dto.totalimp());
		        	vigen.setTotalnac(dto.totalnac());
		        	vigen.setTpprd(dto.tpprd());
		        	
		        	if(tpdcre.equals("N")) {
		        		
		        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");  
		        	    Date date = dateFormat.parse(vigen.getDtregistro());
		        	    Date time = timeFormat.parse(vigen.getHrregistro());
		        	    Timestamp timestamp = new Timestamp(date.getTime() + time.getTime());
		        	    long oneAndHalfDaysInMillis = (long) (regra.get().getCarencia() * 24 * 60 * 60 * 1000);
		                timestamp.setTime(timestamp.getTime() + oneAndHalfDaysInMillis);
		                
		        		vigen.setDtvigini(dateFormat.format(timestamp)); 
			        	vigen.setHrvigini(timeFormat.format(timestamp));
			        	
			        	vigen.setDtvigfim("");
			        	vigen.setHrvigfim("");
			        	
			        	vigenService.create(vigen, request);
		        	}
		        	
		        	if(tpdcre.equals("S")) {
		        		vigen.setDtvigini(dto.dtregistro()); // após o insert, fazer update encerrando vigência do dcrant
			        	vigen.setHrvigini(dto.hrregistro());
			        	
			        	vigen.setDtvigfim("");
			        	vigen.setHrvigfim("");
			        	
			        	String dcrant = vigen.getDcrant();
			        	
			        	vigenService.create(vigen, request);
			        	Optional<Dcrvigen> vigenOld = vigenService.getByKey(dcrant);
			        	vigenOld.get().setDtvigfim(Auxiliar.getDtFormated());
			        	vigenOld.get().setHrvigfim(Auxiliar.getHrFormated());
			        	vigenService.update(vigenOld.get(), request);
			     
		        	}
		        	
		        	if(tpdcre.equals("R")) {
		        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");  
		        	    Date date = dateFormat.parse(vigen.getDtregistro());
		        	    Date time = timeFormat.parse(vigen.getHrregistro());
		        	    Timestamp timestamp = new Timestamp(date.getTime() + time.getTime());
		        	    long oneAndHalfDaysInMillis = (long) (regra.get().getCarencia() * 24 * 60 * 60 * 1000);
		                timestamp.setTime(timestamp.getTime() + oneAndHalfDaysInMillis);
		                
		        		vigen.setDtvigini(dateFormat.format(timestamp)); 
			        	vigen.setHrvigini(timeFormat.format(timestamp));
			        	
			        	vigen.setDtvigfim("");
			        	vigen.setHrvigfim("");
			        	
			        	vigenService.update(vigen, request);
		        	}
		        	
	        	}catch (Exception ae) {
	    		    procc.get().setStatus(status);
	    		    dcrProccservice.update(procc.get(), request);
	        	}   
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
	


	@PutMapping(value = "/geraDiagnostico", produces = "application/json")
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> geraDiagnostico(@RequestBody GeraDiagnosticoDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrproto> lista = service.getByKey(dto.protdcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse protocolo já existe!");
	        }
		
			//Marca anterior como histórico
			service.geraHistorico(dto.idmatriz(), dto.partnumpd(), dto.tpprd());


			//grava novo
	        service.create(dto, request);
	        

			//atualiza valores do processo
	        DcrproccKey key = new DcrproccKey();
	        key.setIdmatriz(dto.idmatriz());
	        key.setPartnumpd(dto.partnumpd());
	        key.setTpprd(dto.tpprd());	        
	        Optional<Dcrprocc> procc = dcrProccservice.getByKey(key);	       
			if(procc.isPresent()) {
	        	procc.get().setTaxausd(dto.taxausd());
	        	procc.get().setTotalnac(dto.totalnac());
	        	procc.get().setTotalimp(dto.totalimp());
	        	procc.get().setCustotal(dto.custotal());
	        	procc.get().setCoefred(dto.coefred());
	        	procc.get().setIitotal(dto.iitotal());
	        	procc.get().setIireduzido(dto.iireduzido());	        	
	        	dcrProccservice.update(procc.get(), request);
	        }
	        

			//Associa pendencias abertaas de diagnóstico ao protocolo (FLEX3FLW='DIAG')
			if(dto.tpprd().equals("PC")){
				pendAstecService.vinculaProtocolo(dto.idmatriz(), dto.protdcre());
			}else{
				pendService.vinculaProtocolo(dto.idmatriz(), dto.partnumpd(), dto.protdcre());
			}
			

	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Diagnóstico gerado com sucesso!");
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@DeleteMapping(value = "/deletaDiagnostico", produces = "application/json")
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deletaDiagnostico(@RequestParam Integer idmatriz, @RequestParam String protdcre, @RequestParam String partnumpd, @RequestParam String tpprd, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrproto> lista = service.getByKey(protdcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse protocolo não existe!");
	        }
		
	        service.delete(lista.get());
	        
	        DcrproccKey key = new DcrproccKey();
	        key.setIdmatriz(idmatriz);
	        key.setPartnumpd(partnumpd);
	        key.setTpprd(tpprd);
	        
	        Optional<Dcrprocc> procc = dcrProccservice.getByKey(key);
	       
	        if(procc.isPresent()) {
	        	procc.get().setTaxausd(Double.valueOf(0));//new Double(0)); //j4 - deprecated
	        	procc.get().setTotalnac(Double.valueOf(0));
	        	procc.get().setTotalimp(Double.valueOf(0));
	        	procc.get().setCustotal(Double.valueOf(0));
	        	procc.get().setCoefred(Double.valueOf(0));
	        	procc.get().setIitotal(Double.valueOf(0));
	        	procc.get().setIireduzido(Double.valueOf(0));
	        	
	        	dcrProccservice.update(procc.get(), request);
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
	


	@DeleteMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Deleta um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum protocolo encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> delete(@RequestParam String protdcre) {
	
		try {
			
			Optional<Dcrproto> lista = service.getByKey(protdcre);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhum protocolo encontrado!");
	        }
		
	        service.delete(lista.get());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Protocolo deletado com sucesso!");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}



    @PutMapping(value = "/registrarDCRe", produces = "application/json")
	@Operation(summary = "Altera um protocolo")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "protocolo não encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> registrarDCRe(@RequestBody RegistrarDCReDTO dto, HttpServletRequest request) {
	
		try {
	
			Optional<Dcrvigen> lista =  dcrvigenService.getByKey(dto.dcre());
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse número de DCR-e já existe!");
	        }
		

            //yyyy MM dd
            //0123 45 67
            String dd   = dto.dtregistro().substring(6, 8);
            String mm   = dto.dtregistro().substring(4, 6);
            String yyyy = dto.dtregistro().substring(0, 4);
            String dttime = dd +' '+mm+' '+yyyy+' '+dto.hrregistro();
            Date dt = Auxiliar.getStrToDate(dttime, "dd MM yyyy hh:mm:ss");
           
            
            //Calc. Vigency date - add 48 hours:            
            Date dtFuture = Auxiliar.addDaysToDate(dt, 2);            
            String dtvigini =  Auxiliar.getDateToStr(dtFuture, "yyyyMMdd");
            String hrvigini = dto.hrregistro();
            

            //Save DCR-e
            Dcrvigen dcr = new Dcrvigen(
                dto.dcre(), dto.idmatriz(), dto.partnumpd(), dto.tpprd(), 
                dto.dtregistro(), dto.hrregistro(), dto.dcrant(), dtvigini, hrvigini,
                dto.taxausd(), dto.totalnac(), dto.totalimp(), dto.custotal(), 
                dto.coefred(), dto.iitotal(), dto.iireduzido());
            dcrvigenService.create(dcr, request);
    

			//Atualiza valores do processo (tpdcre, dtregistro, hrregistro)
	        DcrproccKey key = new DcrproccKey();
	        key.setIdmatriz(dto.idmatriz());
	        key.setPartnumpd(dto.partnumpd());
	        key.setTpprd(dto.tpprd());	        
	        Optional<Dcrprocc> procc = dcrProccservice.getByKey(key);	       
			if(procc.isPresent()) {
	        	procc.get().setTpdcre(dto.tpdcre());
	        	procc.get().setDtregistro(dto.dtregistro());
	        	procc.get().setHrregistro(dto.hrregistro());
                procc.get().setStatus(6); //status registrado
	        	dcrProccservice.update(procc.get(), request);
	        }
			

	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Registro DCR-e gravado com sucesso!");
					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	

}
