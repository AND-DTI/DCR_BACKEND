package com.dcr.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/external")
@PreAuthorize("@ipAddressValidation.vote(authentication, this, null) == T(org.springframework.security.access.AccessDecisionVoter).ACCESS_GRANTED")
public class ExternalAccessController {

	
	@GetMapping(value = "/health-check", produces = "application/json")
	public ResponseEntity<String> healthCheck(@RequestHeader String username, HttpServletRequest request) {
	    return ResponseEntity.status(HttpStatus.OK)
	            .header("Accept", "application/json")
	            .body("Ok");
	}
	
	@GetMapping(value = "/health-check2", produces = "application/json")
	public ResponseEntity<String> healthCheck2(@RequestHeader String username, HttpServletRequest request) {
	    return ResponseEntity.status(HttpStatus.OK)
	            .header("Accept", "application/json")
	            .body("Ok");
	}
	
}
