package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Dcrproto;
import com.dcr.api.model.dto.DcrproccDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.repository.as400.DcrprotoRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrprotoService {

	@Autowired
	DcrprotoRepository repository;
	
	public Dcrproto create( HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrproto dcr = new Dcrproto();
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrproto update(HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		Dcrproto dcr = new Dcrproto();
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrproto> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrproto> getByKey(String id) {
		return repository.findById(id);
	}
}
