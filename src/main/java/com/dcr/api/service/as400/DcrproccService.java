package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.dto.DcrproccDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.repository.as400.DcrproccRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrproccService {

	@Autowired
	DcrproccRepository repository;
	
	public Dcrprocc create(DcrproccDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrprocc dcr = new Dcrprocc();
		
		DcrproccKey key = new DcrproccKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setStatus(dto.status());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setRespstaus(dto.respstatus());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrprocc update(DcrproccDTO dto, Dcrprocc dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setRespstaus(dto.respstatus());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrprocc setStatus(Dcrprocc dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {				
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrprocc> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrprocc> getByKey(DcrproccKey dto) {

		return repository.findById(dto);
	}
}
