package com.dcr.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.as400.Pstruc;
import com.dcr.api.model.dto.CadppbDTO;
//import com.dcr.api.model.projection.Nivel2Projection;
//import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.model.projection.TpprdProjection;
import com.dcr.api.repository.as400.PstrucRepository;
import com.dcr.api.service.as400.CadppbService;
import com.dcr.api.utils.Auxiliar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	CadppbService service;	
	@Autowired
	PstrucRepository repoPstruc;
	
	@GetMapping(value = "/getAll", produces = "application/json")
	@Operation(summary = "Busca todas as regras")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum produto encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAll() {
	
		try {
			List<Cadppb> lista = service.getAll();
	        if (lista.isEmpty()) {
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
	


	@GetMapping(value = "/getByKey", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	//public ResponseEntity<Object> getByKey(@RequestParam String cdprd, @RequestParam String tpprd) {
	public ResponseEntity<Object> getByKey(@RequestParam String partnumpd) { //added j4
	
		try {
			//ProdutoKey key = new ProdutoKey();
			//key.setCdprd(cdprd);
			//key.setTpprd(tpprd);
			
			//Optional<Cadppb> lista = service.getByID(key);
			Optional<Cadppb> lista = service.getByID(partnumpd);
	        if (lista.isEmpty()) {
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
	


	@GetMapping(value = "/getByTpprd", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getByTpprd(@RequestParam List<String> listaTpprd) {
		
	
		try {
			
			TpprdProjection lista = service.getByTpprd(listaTpprd);
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
	


	@PutMapping(value = "/create", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@RequestBody CadppbDTO dto, HttpServletRequest request) {
	
		try {
			//ProdutoKey key = new ProdutoKey();
			//key.setCdprd(dto.cdprd());
			//key.setTpprd(dto.tpprd());
			

			//Optional<Cadppb> lista = service.getByID(key);
			Optional<Cadppb> lista = service.getByID(dto.partnumpd()); //j4 added
	        if (!lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Esse produto já existe!");
	        }
		

			//Valida código na estrutura			
			List<Pstruc> produtos = null;
			//Boolean codEncontrado = false;
			if (!dto.tpprd().equals("PC")){
				produtos = repoPstruc.pegaProdutoAcabado(dto.partnumpd());
			}else{
				produtos = repoPstruc.pegaASTEC(dto.partnumpd());
			}
			
			if (produtos.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
		    			.header("Accept", "application/json")
		        		.body("Partnumber não encontrado na estrutura de produto");       
			}
			
			
	        service.create(dto, request);
	        return ResponseEntity.status(HttpStatus.CREATED)
		        	.header("Accept", "application/json")
		            .body("OK");

					
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@PutMapping(value = "/update", produces = "application/json")
	@Operation(summary = "Busca todos as responsáveis")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Nenhum responsável encontrado!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> update(@RequestBody CadppbDTO dto, HttpServletRequest request) {
	
		try {
			//ProdutoKey key = new ProdutoKey();  //j4
			//key.setCdprd(dto.cdprd());  //j4
			//key.setTpprd(dto.tpprd());  //j4
			
			//Optional<Cadppb> lista = service.getByID(key); //j4
			Optional<Cadppb> lista = service.getByID(dto.partnumpd()); //added j4
	        if (lista.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .header("Accept", "application/json")
	                    .body("Associação não encontrada!");
	        }
		
	        service.update(lista.get(), dto,  request);
	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("OK");
		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
	


	@DeleteMapping(value = "/delete", produces = "application/json")
	@Operation(summary = "Remove ppb dos partnumbers na lista")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Ok"),
	        @ApiResponse(responseCode = "400", description = "Partnumber não encontrado para remoção!"),
	        @ApiResponse(responseCode = "500", description = "Error!")
	})
	@ResponseStatus(HttpStatus.OK)
	//public ResponseEntity<Object> delete(@RequestParam String cdprd, @RequestParam String tpprd) {
    //public ResponseEntity<Object> delete(@RequestParam List<Nivel2Projection> itens) {
    public ResponseEntity<Object> delete(@RequestParam List<String> itens) {
	
		try {
			//ProdutoKey key = new ProdutoKey();
			//key.setCdprd(cdprd);
			//key.setTpprd(tpprd);
			
			//Optional<Cadppb> lista = service.getByID(key);
			Integer nao_encontrados = 0;
			for (String partnumber : itens) {
							
				Optional<Cadppb> lista = service.getByID(partnumber); //added j4				
				if (lista.isEmpty()) {
					nao_encontrados += 1;
					//return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					//		.header("Accept", "application/json")
					//		.body("Nenhum produto encontrado!");
				}else{
					//service.delete(lista.get());
				}							

			}

	        return ResponseEntity.status(HttpStatus.OK)
		        	.header("Accept", "application/json")
		            .body("PPB removido com sucesso!");

		} catch (Exception ae) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) 
		    			.header("Accept", "application/json")
		        		.body(ae.getMessage());                
		}   
	}
}
