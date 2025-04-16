package com.dcr.api.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.response.MatriprdByTpprdResponse;
import com.dcr.api.response.MatriprdResponse;
import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.response.ProdutoPendenciaResponse2;
//import com.dcr.api.response.ProdutoPendenciaSimplesResponse;
import com.dcr.api.response.ProdutoSemListaResponse;
import com.dcr.api.service.as400.MatriprdService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/matriz/produto/detalhe")
public class DetalheProdutoController {

	@Autowired
	MatriprdService service;
	


	@GetMapping(value = "/getDetail", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum detalhe de produto encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDetail(@RequestParam Integer idmatriz) {
	
		try {

			MatriprdResponse lista = service.getDetail(idmatriz);
			  if (lista.getIdMatriz() == null) {
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                    .header("Accept", "application/json")
		                    .body("Nenhum produto encontrado!");
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
	

	
	@GetMapping(value = "/getDetailByTpprd", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum detalhe de produto encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDetail(@RequestParam List<String> listaTpprd) {
	
		try {

			List<MatriprdByTpprdResponse> lista = service.getDetailByTpprd(listaTpprd);
			  if (lista.isEmpty()) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                    .header("Accept", "application/json")
		                    .body("Nenhum produto encontrado!");
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

	

	@GetMapping(value = "/getProdutoPendencia", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProdutoPendencia(@RequestParam Integer idmatriz, @RequestParam String partnumpd) {
	
		try {

			ProdutoPendenciaResponse2 lista = service.getProdutoPendencia(idmatriz, partnumpd); //j4 - old ProdutoPendenciaSimplesResponse
	        if (lista.getIdMatriz() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
	        }
	        Auxiliar.formatResponse(lista);
            Auxiliar.formatResponse(lista.getCor());
            Auxiliar.formatResponseList2(lista.getPendencias());
			Auxiliar.formatResponseList2(lista.getInsumos());
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body(lista);
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@GetMapping(value = "/getProdutoPendenciaDiagnostico", produces = "application/json") //j5 - added
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getProdutoPendenciaDiagnostico(@RequestParam Integer idmatriz, @RequestParam String partnumpd) {
	
		try {

			ProdutoPendenciaResponse lista = service.getProdutoPendenciaDiagnostico(idmatriz, partnumpd); 
	        if (lista.getIdMatriz() == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de diagnóstico encontrada!");
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
	


	@GetMapping(value = "/getPendentes", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendentes(@RequestParam List<Integer> status) {
	
		try {

			List<ProdutoPendenciaResponse> lista = service.getTodasAsPendencias(status);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
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
	


	@GetMapping(value = "/getPendentesSemLista", produces = "application/json")
	@Operation(summary = "Busca um tipo de produto")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhuma pendência de produto encontrada!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getPendentesSemLista(@RequestParam List<Integer> status) {
	
		try {

			List<ProdutoSemListaResponse> lista = service.getPendenciasSemLista(status);
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .header("Accept", "application/json")
	                    .body("Nenhuma pendência de produto encontrada!");
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

}
