package com.dcr.api.controller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.service.as400.DcrlayoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.microsoft.playwright.*;
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.BrowserType;
//import com.microsoft.playwright.Playwright;
//import java.util.HashMap;
//import javax.net.ssl.HttpsURLConnection;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/rpa")
public class RPAController {



	@Autowired
	DcrlayoutService txtService;


	
	@GetMapping(value = "/transmiteDiagnostico", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Envia TXT para Diagnóstico")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> sendDiagnostico(@RequestParam Integer idmatriz,@RequestParam String partnumpd,@RequestParam String tpprd, String path ) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		
		try {
			String fileName = txtService.gerarArquivoTXT_Linux(idmatriz, partnumpd, tpprd, path); 			
			var file = new File(fileName);
	        var path0 = Paths.get(file.getAbsolutePath()); 	        
			String content = new String(Files.readAllBytes(path0), StandardCharsets.UTF_8);	

			Browser browser = loginPlaywright();
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
		            .body("Erro ao enviar arquivo para diagnóstico! [Erro: "+e.getMessage()+"]");
		}
				
	}	

	private Browser loginPlaywright(){


		Browser browser = null;

		try{
            
            //1. OPEN BRWOSER SESSION
            Playwright playwright = Playwright.create();            
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                                
            
            //2. NAVIGATE TO LOGIN PAGE
            String HOST_URL = "https://www4c.receita.fazenda.gov.br";
			String LOGIN_URL0 = HOST_URL+"/g33159/jsp/logon.jsp?ind=2";
            String LOGIN_URL = HOST_URL+"/g33159/jsp/LogonCertificado.jsp?ind=2";
            Page page = browser.newPage();
            page.navigate(LOGIN_URL0);
			page.navigate(LOGIN_URL);

			
            //3. WAIT USER TO SELECT CERTIFICATE            
            //check if cookie was set cookie...        
            //Thread.sleep(2000);            
            /*List<Cookie> cookies = browser.contexts().get(0).cookies();
            if(cookies.isEmpty()){
                session.setStatus(500);
                session.setMessage("Sessão do usuário sem certificado selecionado!");
                return session;
            }*/
            

            //4. STORE SESSION COOKIES
            /*Map<String, String> loginCookies = new HashMap<>();            
            for (Cookie cookie : cookies) {
                loginCookies.put(cookie.name, cookie.value);                
            }
            Map<String, String> defaultHeaders = buildDefaultHeaders(loginCookies);*/


            //5. CLOSE SESSION
            //playwright.close();


                       

        } catch (Exception e) {
           //salvaLogErro("TesteController-loginPlaywright()", e.getMessage()); 
        }


		return browser;

	}
    

}
