package com.dcr.api.controller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.rpa.response.RPAResponse;
import com.dcr.api.rpa.response.SessionResponse;
import com.dcr.api.rpa.service.RPAService;
import com.dcr.api.service.as400.DcrlayoutService;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.microsoft.playwright.*;
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.BrowserType;
//import com.microsoft.playwright.Playwright;
//import java.util.HashMap;
//import javax.net.ssl.HttpsURLConnection;
//import com.dcr.api.utils.RequestUtil;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import com.dcr.api.model.rpa.DiagnosticoDCR;
//import com.microsoft.playwright.options.Cookie;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/rpa")
@ConditionalOnProperty(value= "env.so", havingValue= "windows", matchIfMissing= false)
public class RPAController {


	@Autowired
	DcrlayoutService txtService;
 	@Autowired
    UserService userService;
	@Autowired
    Environment env;
	@Autowired
	RPAService rpaService;

	

	@GetMapping(value = "/transmiteDiagnostico", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Envia TXT para Diagnóstico")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> sendTXTDiagnostico(
		@RequestParam Integer idmatriz,
		@RequestParam String partnumpd,
		@RequestParam String tpprd, 
		@RequestParam String fileName,
		@RequestParam Boolean closeRPASession){
		
		
		try {

			//Gera arquivo - already requested by front to save on Linux - generate to RPA server			
			String fileName2 = txtService.gerarArquivoTXT_Linux(idmatriz, partnumpd, tpprd, env.getProperty("storage.fileserver"));
			if(fileName2.equals(fileName)){ }
			//var file = new File(fileName);
	        //var path0 = Paths.get(file.getAbsolutePath()); 	        
			//String content = new String(Files.readAllBytes(path0), StandardCharsets.UTF_8);	
			
			SessionResponse currSession = rpaService.loginPlaywright2(); //transmissão sempre irá logar           
            if(currSession.getStatus() != 200){ 
				closeSession(true);
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)                         
                    .body("Falha ao realizar login no RPA. "+currSession.getMessage());                
            }
						
			RPAResponse response= rpaService.TransmiteTXT(currSession, fileName);			    			

			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response/*response.getReponseEntity()*/); 
            			
		} catch (Exception e) { //catch (IOException e) {
			doLogErro("sendTXTDiagnostico()", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao enviar arquivo para diagnóstico! [Erro: "+e+"]");
		} finally{			 			
			closeSession(closeRPASession);	
		}
				
	}	



	@GetMapping(value = "/recuperaDiagnostico", produces = "application/json") 
	@Operation(summary = "Recupera Diagnóstico")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> recuperaDiagnostico(
		@RequestParam Integer idmatriz,
		@RequestParam String partnumpd,
		@RequestParam String tpprd, 
		@RequestParam String idTransmissao, 
		@RequestParam Boolean DoNewLogin,
		@RequestParam Boolean closeRPASession ){
		
		
		try {

			SessionResponse currSession = null;
			
			if(DoNewLogin){
				currSession = rpaService.loginPlaywright2();            
				if(currSession.getStatus() != 200){ 
					closeSession(true);					
					return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)                         
						.body("Falha ao realizar login no RPA. "+currSession.getMessage());                
				}
			}else{
				currSession = rpaService.mountCurrSession(); 								
				Page page = currSession.getPage();
				String URL= currSession.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";            
				page.navigate(URL);				
			}

			RPAResponse response = rpaService.RecuperaDiagnostico(currSession, idTransmissao);		                            						
			
			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response); 
            			
	    
		} catch (Exception e) { //catch (IOException e) {
			doLogErro("recuperaDiagnostico()", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao recuperar diagnóstico para o envio "+idTransmissao+"! [Erro: "+e+"]");
		} finally{			 			
			closeSession(closeRPASession);
		}
				
	}	



	@GetMapping(value = "/recuperaTotaisDiagnostico", produces = "application/json") 
	@Operation(summary = "Recupera Resumo/Totais do Diagnóstico")	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> recuperaTotaisDiagnostico(		
		@RequestParam String protocolo,		
		@RequestParam Boolean DoNewLogin,
		@RequestParam Boolean closeRPASession ){
		
		
		try {
			
			SessionResponse currSession = null;

			if(DoNewLogin){
				currSession = rpaService.loginPlaywright2();            
				if(currSession.getStatus() != 200){ 
					//currSession.getBrowser().close();
					closeSession(true);
					return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)                         
						.body("Falha ao realizar login no RPA. "+currSession.getMessage());                
				}
			}else{
				currSession = rpaService.mountCurrSession(); 								
				Page page = currSession.getPage();
				String URL= currSession.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";            
				page.navigate(URL);				
			}

			RPAResponse response = rpaService.RecuperaResumoDiagnostico(currSession, protocolo);		         			

			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response); 
            			
	    
		} catch (Exception e) { 
			doLogErro("recuperaTotaisDiagnostico", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao recuperar resumo do diagnóstico para o protocolo "+protocolo+"! [Erro: "+e+"]");
		} finally{			 			
			closeSession(closeRPASession);
		}
				
	}	



	@GetMapping(value = "/transmiteDiagnosticoSIMULA", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Envia TXT para Diagnóstico - Simulação")	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> sendTXTDiagnosticoSIMULA(
		@RequestParam Integer idmatriz,
		@RequestParam String partnumpd,
		@RequestParam String tpprd, 
		@RequestParam String fileName,
		@RequestParam Boolean comErroLayout,
		@RequestParam Boolean closeRPASession,
		Boolean testaLogin)  {
		
		
		try {
			
			SessionResponse currSession = null;
			
			if(testaLogin!=null && testaLogin){		
				currSession = rpaService.loginPlaywright2();
				//currSession = rpaService.loginPlaywright3();            
				if(currSession.getStatus() != 200){ 					
					closeSession(true);				
					return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)                         
						.body("Falha ao realizar login no RPA. "+currSession.getMessage());                
				}
			}
						
			RPAResponse response= rpaService.TransmiteTXT_Simulacao(currSession, fileName, comErroLayout);
			//recupera diagnostico sem logar novmente:
			//response = rpaService.RecuperaDiagnosticoPlaywright_Simulacao(currSession, response.getRecordKey(), false);        			

			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response/*response.getReponseEntity()*/); 
            			
	    
		} catch (Exception e) { //catch (IOException e) {
			doLogErro("sendTXTDiagnosticoSIMULA()", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao enviar arquivo para diagnóstico! [Erro: "+e+"]");
		} finally{				
			closeSession(closeRPASession);
		}
				
	}	



	@GetMapping(value = "/recuperaDiagnosticoSIMULA", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Recupera Diagnóstico - Simulação")	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> recuperaDiagnosticoSIMMULA(
		@RequestParam Integer idmatriz,
		@RequestParam String partnumpd,
		@RequestParam String tpprd, 
		@RequestParam String idTransmissao, 
		@RequestParam Boolean comErro,
		@RequestParam Boolean DoNewLogin,
		@RequestParam Boolean closeRPASession,
		Boolean testaLogin,
		Integer numTentativa){
		
		
		try {
			
			//simula ainda não processado
			if(numTentativa!=null && numTentativa < 2){
				RPAResponse response0 = new RPAResponse();
				response0.setStatusCode(400);
				response0.setMsg("Protocolo para o envio "+idTransmissao+" ainda não processado!");				
				return ResponseEntity.status(response0.getStatusCode()).body(response0); 
			}

			SessionResponse currSession = null;
			
			if(testaLogin!=null && testaLogin){	
				if(DoNewLogin){
					currSession = rpaService.loginPlaywright2();            
					if(currSession.getStatus() != 200){ 
						closeSession(true);                            
						return ResponseEntity
							.status(HttpStatus.BAD_REQUEST)                         
							.body("Falha ao realizar login no RPA. "+currSession.getMessage());                
					}
				}else{
					currSession = rpaService.mountCurrSession(); 								
					Page page = currSession.getPage();
					String URL= currSession.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";            
					page.navigate(URL);				
				}
			}

			RPAResponse response = rpaService.RecuperaDiagnostico_Simulacao(currSession, idTransmissao, comErro);		         			

			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response); 
            			
	    
		} catch (Exception e) { //catch (IOException e) {
			doLogErro("recuperaDiagnostico()", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao recuperar diagnóstico para o envio "+idTransmissao+"! [Erro: "+e+"]");
		} finally{			 			
			closeSession(closeRPASession);
		}
				
	}	



	@GetMapping(value = "/recuperaTotaisDiagnosticoSIMULA", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Recupera Resumo/Totais do Diagnóstico - Simulação")	
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> recuperaTotaisDiagnosticoSIMULA(		
		@RequestParam String protocolo,		
		@RequestParam Boolean DoNewLogin,
		@RequestParam Boolean closeRPASession,
		Boolean testaLogin ){
		
		
		try {
			
			SessionResponse currSession = null;
			
			if(testaLogin!=null && testaLogin){	
				if(DoNewLogin){
					currSession = rpaService.loginPlaywright2();            
					if(currSession.getStatus() != 200){ 										
						closeSession(true);							
						return ResponseEntity
							.status(HttpStatus.BAD_REQUEST)                         
							.body("Falha ao realizar login no RPA. "+currSession.getMessage());                
					}
				}else{
					currSession = rpaService.mountCurrSession(); 								
					Page page = currSession.getPage();
					String URL= currSession.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";            
					page.navigate(URL);				
				}
			}

			RPAResponse response = rpaService.RecuperaResumoDiagnostico_Simulacao(currSession, protocolo);		          			

			return ResponseEntity
				.status(response.getStatusCode()) 
				.body(response); 
            			
	    
		} catch (Exception e) { 
			doLogErro("recuperaTotaisDiagnosticoSIMULA()", e.toString()); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao recuperar diagnóstico para o protocolo "+protocolo+"! [Erro: "+e+"]");
		} finally{			 			
			closeSession(closeRPASession);
		}
				
	}	




	@GetMapping(value = "/closeRPASession", produces = "application/json") 
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> closeRPASession(@RequestParam String type){
				
		try {

			rpaService.closeSesssion(type);
			
	        return ResponseEntity
	                .ok()	                						                
	                .body("Seção RPA ("+type+") encerrada!"); 

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        	.header("Accept", "application/json")
		            .body("Erro ao limpar sessções ("+type+") do RPA! [Erro: "+e+"]");
		}

	}	






	
	@GetMapping(value = "/transmiteDiagnostico0", produces = "application/json") //charset=UTF-8
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> sendDiagnostico0(@RequestParam Integer idmatriz,@RequestParam String partnumpd,@RequestParam String tpprd, String path ) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		
		try {
			String fileName = txtService.gerarArquivoTXT_Linux(idmatriz, partnumpd, tpprd, path); 			
			var file = new File(fileName);
	        var path0 = Paths.get(file.getAbsolutePath()); 	        
			String content = new String(Files.readAllBytes(path0), StandardCharsets.UTF_8);	

			Browser browser = rpaService.loginPlaywright();
			System.out.println("Cookies --> "+browser.contexts().get(0).cookies());

			//Abre frame...
			//https://www4c.receita.fazenda.gov.br/g36162/html/AberturaZFM.html
			//https://www4c.receita.fazenda.gov.br/g36162/html/FrameDCRE.html

			//Envia diagnostico (Transmitir Declaração)
			//POST:
			//https://www4c.receita.fazenda.gov.br/g36162/navEnviarDCRE
			//Dados do formulário:
			//transacao		TR-DECL
			//etapa			Envio
			//arquivo		(binário)
			/* 
			 ------WebKitFormBoundaryblc5XrbGXFxx8VKW
			Content-Disposition: form-data; name="transacao"

			TR-DECL
			------WebKitFormBoundaryblc5XrbGXFxx8VKW
			Content-Disposition: form-data; name="etapa"

			Envio
			------WebKitFormBoundaryblc5XrbGXFxx8VKW
			Content-Disposition: form-data; name="arquivo"; filename="MN30053_MLGB140RZA (4).txt"
			Content-Type: text/plain


			------WebKitFormBoundaryblc5XrbGXFxx8VKW--
			*/

			//Referer:
			//https://www4c.receita.fazenda.gov.br/g36162/navDCRE?transacao=TR-DECL&etapa=Preparo



			//Retornar protocolo
	        return ResponseEntity
	                .ok()	                
					.contentType(new MediaType("text", "plain", StandardCharsets.UTF_8))
	                .contentLength(file.length())
	                .body(content); 
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Erro ao enviar arquivo para diagnóstico! [Erro: "+e+"]");
		}
				
	}	


	private void closeSession(Boolean closeRPASession){

		if(closeRPASession){
			rpaService.closeSesssion("context");
			rpaService.closeSesssion("browser");
		}

	}


    private void doLogErro(String processo, String errorMsg){
        		
        Auxiliar.salvaLogErro(processo, errorMsg, env.getProperty("storage.approot"));
        
    }

	
}
