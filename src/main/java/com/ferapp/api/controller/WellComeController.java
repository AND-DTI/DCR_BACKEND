package com.ferapp.api.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/wellcome")
public class WellComeController {

    @Value("${app.name:apiName}")
    String app_name;

    @GetMapping(value = "/showuser", produces = "application/json")
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado!"

            ) })

    public ResponseEntity<String> showuser(HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body("Wellcome to API - " + app_name + "!");

    }

}
