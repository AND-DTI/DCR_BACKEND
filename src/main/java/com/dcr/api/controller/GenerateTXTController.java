package com.dcr.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/txt")
public class GenerateTXTController {

	@Autowired
	DcrlayoutService txtService;
	
	@GetMapping(value = "/generate", produces = "application/json")
	@Operation(summary = "Gera txt")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> generate(@RequestParam Integer idmatriz,@RequestParam String partnumpd,@RequestParam String tpprd ) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		try {
			txtService.gerarArquivoTXT(idmatriz, partnumpd, tpprd);
			var file = new File("arquivoTeste2.txt");
	        var path = Paths.get(file.getAbsolutePath());
	        var resource = new ByteArrayResource(Files.readAllBytes(path));
	        return ResponseEntity
	                .ok()
	                .contentType(MediaType.TEXT_PLAIN)
	                .contentLength(file.length())
	                .body(resource);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("Erro na geração do arquivo!");
		}
		
	}
    
}
