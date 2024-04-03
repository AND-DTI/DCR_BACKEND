package com.dcr.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/securityuser")
public class SecurityUserController {

	@GetMapping(value = "/checktoken", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> checktoken() {
		return ResponseEntity.status(HttpStatus.OK) 
		   .header("Accept", "application/json")
		      .body("OK");                
	}
}
