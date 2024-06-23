package com.dcr.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HealthController {

	@GetMapping(value = "/health-check", produces = "application/json")
	public ResponseEntity<String> healthCheck(HttpServletRequest request) {
	
	    return ResponseEntity.status(HttpStatus.OK)
	            .header("Accept", "application/json")
	            .body("Ok");
	}
}
