package com.dcr.api.controller;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dcr.api.configs.security.AESUtil;
import com.dcr.api.configs.security.Security;

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



	@GetMapping(value = "/encrypt", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> encrypt(@RequestParam String input) 
	    throws Exception {

		//String encryptedInput = Security.encrypt_default(input);
		//String encryptedInput = Security.encrypt_simple(input);
		String encryptedInput = "";



		String original = "rakesh";
        String data = "CfPcX0G+e7TLKKMyyvrvrQ==";
        String k = "qertyuiopasdfghw"; //AES key length must be 16
        String k1 = "qertyuio";  // DES key length must be 8 
        String data1 = "rakesh";
        String data2 = "nAtvNq7uHKE=";
        String Algo= "DES";
        String Algo1= "AES";
        //Decrypt decrypter = new Decrypt();
		System.out.println("Original String: " + original);

		System.out.println("encrypted String in DES: " + AESUtil.encrypt2(data1, k1, Algo));
		System.out.println("Decrypted String in DES: " + AESUtil.decrypt2(data2, k1, Algo));
		System.out.println("encrypted String in AES: " + AESUtil.encrypt2(data1, k, Algo1));
		System.out.println("Decrypted String in AES: " + AESUtil.decrypt2(data, k, Algo1));


		System.out.println("decript: " + AESUtil.decrypt2("[B@49ccccda", k1, Algo));


		return ResponseEntity.status(HttpStatus.OK) 
		   .header("Accept", "application/json")
		      .body(encryptedInput);                
	}


	@GetMapping(value = "/dencrypt", produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> dencrypt(@RequestParam String input) 
	    throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidAlgorithmParameterException, 
		NoSuchPaddingException, BadPaddingException, ClassNotFoundException, IOException, InvalidKeySpecException {

		String dencryptedInput = Security.dencrypt_default(input);
		//String dencryptedInput = Security.dencrypt_simple(input);

		return ResponseEntity.status(HttpStatus.OK) 
		   .header("Accept", "application/json")
		      .body(dencryptedInput);                
	}



}
