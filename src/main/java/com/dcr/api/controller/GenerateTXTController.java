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
//import io.swagger.v3.oas.models.media.Encoding;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/txt")
public class GenerateTXTController {



	@Autowired
	DcrlayoutService txtService;


	
	@GetMapping(value = "/generate", produces = "application/json") //charset=UTF-8
	@Operation(summary = "Gera txt")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> generate(@RequestParam Integer idmatriz,@RequestParam String partnumpd,@RequestParam String tpprd ) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		
		try {
			String fileName = txtService.gerarArquivoTXT2(idmatriz, partnumpd, tpprd, "diagnostico"); //old .gerarArquivoTXT(
			var file = new File(fileName);//old "arquivoTeste2.txt"
	        var path = Paths.get(file.getAbsolutePath());
	        //var resource = new ByteArrayResource(Files.readAllBytes(path));			
			String content = new String(Files.readAllBytes(path), StandardCharsets.ISO_8859_1);
			//byte[] resource2 = Files.readAllBytes(path);
			//String content = new String(resource2, StandardCharsets.ISO_8859_1); //UTF_8
	        return ResponseEntity
	                .ok()
	                //.contentType(MediaType.TEXT_PLAIN)
					.contentType(new MediaType("text", "plain", StandardCharsets.UTF_8))
	                .contentLength(file.length())
	                .body(content); //resource
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Erro na geração do arquivo!");
		}
				
	}
    

}
